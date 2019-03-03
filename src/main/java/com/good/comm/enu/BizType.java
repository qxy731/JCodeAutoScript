package com.good.comm.enu;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 业务类型
 * 
 * @author wu.wei
 *
 */
public class BizType implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * <pre>
     * SYS        系统管理
     * </pre>
     */
	private String name;
	private String desc;

	private static HashMap<String,BizType> bizTypes = new HashMap<String,BizType>();
	public static BizType SYS = new BizType("SYS");
	public static BizType MKT = new BizType("MKT");
	public static BizType CUS = new BizType("CUS");
	public static BizType PRD = new BizType("PRD");
	
	static {
		bizTypes.put(SYS.name(), SYS);
		bizTypes.put(MKT.name(), MKT);
		bizTypes.put(CUS.name(), CUS);
		bizTypes.put(PRD.name(), PRD);
		bizTypes.put("DEFAULT", new BizType("DEFAULT"));
	}

	private BizType(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static BizType valueOf(String name) {
		if (bizTypes.containsKey(name)) {
			return bizTypes.get(name);
		}
		else {
			BizType bt = new BizType(name);
			bizTypes.put(name, bt);
			return bt;
		}
	}
	
	public String toString() {
		return name;
	}
}
