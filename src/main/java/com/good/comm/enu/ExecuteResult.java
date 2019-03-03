package com.good.comm.enu;

/**
 * 执行结果
 * 
 * @author wuwei
 *
 */
public enum ExecuteResult {
    SUCCESS("1"),FAIL("0"),UNKNOWN("2");
    private String value;
    private ExecuteResult(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    public static ExecuteResult getInstance(String str) {
        ExecuteResult ret = UNKNOWN;
        if (str!= null && str.length() > 0 ) {
            if ("1".equals(str) || "true".equalsIgnoreCase(str)) {
                ret = SUCCESS;
            }
            if ("0".equals(str) || "false".equalsIgnoreCase(str)) {
                ret = FAIL;
            }
        }
        return ret;
    }
}
