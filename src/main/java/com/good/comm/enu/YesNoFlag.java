package com.good.comm.enu;

public enum YesNoFlag {
    YES("1"), NO("0");
    private String value;

    private YesNoFlag(String c) {
        value = c;
    }

    public String getValue() {
        return value;
    }

    public static YesNoFlag getInstance(String str) {
        YesNoFlag ret = NO;
        if (str != null && str.length() > 0) {
            if ("1".equals(str) || "true".equalsIgnoreCase(str)) {
                ret = YES;
            }
        }
        return ret;
    }
}
