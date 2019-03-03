package com.good.sys;

public class DbAccessException extends MessageException {
    public DbAccessException(Throwable e) {
        super(e);
    }

    private static final long serialVersionUID = 1L;
}
