package com.good.sys;

public class MessageException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public MessageException() {
        super();
    }

    public MessageException(String errorCode, String errorMessage) {
        super(errorCode + "-" + errorMessage);
        this.errorCode = errorCode;
        this.errorMsg = errorMessage;
    }

    public MessageException(Throwable e) {
        super(e);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMessage) {
        this.errorMsg = errorMessage;
    }
}
