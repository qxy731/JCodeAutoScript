package com.good.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class UUIDGenerator {
	
	private static final String REDIS_KEY_COMMONSEQ = "SPRING_SYSTEM_COMMONSEQ";
	//private static final String REDIS_KEY_WORKID = "SPRING_SYSTEM_WORKID";
	private static final String REDIS_KEY_TIMESEQ = "SPRING_SYSTEM_TIMESEQ";
	
	//private static SecureRandom securerandom = null;
	private static SimpleDateFormat formater = null;
	//private static String systemPid = null;
	//private static long workId = 100000L;
	//private static RedisAtomicLong workCounter = null;
	private static RedisAtomicLong sequenceCounter = null;
	private static RedisAtomicLong timeCounter = null;
	
	/*@Autowired
    private static RedisTemplate<String, ?> redisTemplate = null;*/
	static{
		try{
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getBeanById("redisTemplate");
			formater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//securerandom = SecureRandom.getInstance("SHA1PRNG","SUN");
			//systemPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
			RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
			sequenceCounter = new RedisAtomicLong(REDIS_KEY_COMMONSEQ, connectionFactory);
			timeCounter = new RedisAtomicLong(REDIS_KEY_TIMESEQ, connectionFactory);
			/*workCounter = new RedisAtomicLong(REDIS_KEY_WORKID, connectionFactory);
			if(StringUtils.isEmpty(workId)){
				workId = workCounter.incrementAndGet();
			}*/
		}catch(Exception e){
			//securerandom = new SecureRandom();
		}
	}
	/**
	 * 获得一个全球唯一的32位UUID序列号
	 * @return
	 */
	public static String getUUIDKey(){
        return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获得24位纯数字TimeSequenceKey
	 * 17位时间戳+7位redis序列值.
	 * @return 24位纯数字UUID
	 */
	public static String getTimeSequenceKey(){
		String time = formater.format(new Date());
		String timeSeq = String.format("%07d",timeCounter.incrementAndGet()%10000000);
		return time + timeSeq;
	}
	 
	 /**
	 * @return Long Key
	 */
	public static Long getRedisLongKey(){
		return sequenceCounter.incrementAndGet();
	}
	
	/**
	 * @return Long Key
	 */
	public static String getCommonSequenceKey(){
		return String.format("%020d",sequenceCounter.incrementAndGet()&0x7FFFFFFF).substring(4);
	}
	
	/*public static String getRandomKey(){
		String workid = String.format("%05d", workId%100000);
		String workid = "10000";
		String pid = String.format("%05d",Integer.valueOf(systemPid));
		//long time = System.nanoTime();
		String time =  String.format("%+020d",System.nanoTime()&0x7FFFFFFF).substring(1);
		//String time =  String.format("%020d",System.currentTimeMillis()&0x7FFFFFFF).substring(1);
		String random = String.format("%020d",securerandom.nextLong()&0x7FFFFFFF).substring(1);
		return workid + pid + time + random;
	}*/
	
	
	public static String getBizSequenceKey(String prefix,String suffix){
		String retStr = String.valueOf(getRedisLongKey());
		prefix = prefix==null?"":prefix;
		suffix = suffix==null?"":suffix;
        return prefix + retStr + suffix;
	}
	
	public static String getBizSequenceKeyByPrefix(String prefix){
        return getBizSequenceKey(prefix,null);
	}
	
	public static String getBizSequenceKeyBySuffix(String suffix){
        return getBizSequenceKey(null,suffix);
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		int lenght = 400000;
		long start = Long.valueOf(formater.format(new Date()));
		System.out.println("开始时间为"+start+"====================");
		for(int i=0;i<lenght;i++){
			String tmp = UUIDGenerator.getTimeSequenceKey();
			System.out.println(tmp);
			map.put(tmp+"", tmp);
		}
		long end = Long.valueOf(formater.format(new Date()));
		System.out.println("结束时间为"+end+"====================");
		long minus = end - start;
		System.out.println("消耗时间为"+minus+"ms====================");
		double one = minus/lenght;
		System.out.println("每条uuid生成耗时为"+one+"ms====================");
		int size = map.size();
		if(size != lenght){
			int dup = lenght - size;
			System.out.println("出现了重复条数为"+dup);
		}else{
			System.out.println("此计算过程没有出现重复");
		}
		
		
	}

}
