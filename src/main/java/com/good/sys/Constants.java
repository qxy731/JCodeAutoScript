package com.good.sys;

public class Constants {

    public static String SESSION_USER_KEY = "_USER_KEY";
    public static String SESSION_CONTEXT_PATH = "_CONTEXT_PATH";
    public static String SESSION_AUTHCODE = "_USER_LOGON_AUTHCODE";
    public static String BEAN_SECURITY_METADATA = "securityMetadataSource";
    
    /**
     * 注册的机器
     */
    public static String REDIS_REGISTER_MAP = "REGISTER_MATCHINE";
    /**
     * 获取机器信息的URL
     */
    public static String URL_MONITOR_MACHINE = "/js/collect/allinfo";
    /**
     * 缓存数据同步URL
     */
    public static final String URL_MONITOR_SYNC = "/js/sync/auth";
    /**
     * 新用户初始密码 111111
     */
    public static String INIT_PASSWORD = "96e79218965eb72c92a549dd5a330112";
    
    /**
     * 角色组
     */
    public static String ROLETYPE_GROUP ="2";
    
    /**
     * 角色
     */
    public static String ROLETYPE_ROLE ="1";
}
