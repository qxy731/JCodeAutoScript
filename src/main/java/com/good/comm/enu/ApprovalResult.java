package com.good.comm.enu;

/**
 * 待办任务状态
 * 
 * @author wu.wei
 *
 */
public enum ApprovalResult {
    /**
     * 待审批,审批中,审批通过,审批不通过
     */
    PEDDING("0"),RUNNING("1"),PASS("2"),NOPASS("3");
    private String value;

    private ApprovalResult(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    public static ApprovalResult getInstance(String str) {
        ApprovalResult ret = PEDDING;
        if (str!= null && str.length() > 0 ) {
             if ("1".equals(str)) {
                 return RUNNING;
             }
             if ("2".equals(str)) {
                 return PASS;
             }
             if ("3".equals(str)) {
                 return NOPASS;
             }
        }
        return ret;
    }
}
