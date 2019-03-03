package com.good.comm.enu;

/**
 * 操作类型
 * @author wuwei
 *
 */
public enum FunctionType {
    /**
     * 新增，删除，修改，查询
     */
    INSERT("A"),DELETE("D"),UPDATE("U"),QUERY("Q"),NORMAL("N");
    private String value;
    private FunctionType(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static FunctionType getInstance(String str) {
        FunctionType ret = NORMAL;
        if (str!= null && str.length() > 0 ) {
            if ("A".equals(str)) {
                ret = INSERT;
            }
            if ("D".equals(str)) {
                ret = DELETE;
            }
            if ("U".equals(str)) {
                ret = UPDATE;
            }
            if ("Q".equals(str)) {
                ret = QUERY;
            }
        }
        return ret;
    }
}
