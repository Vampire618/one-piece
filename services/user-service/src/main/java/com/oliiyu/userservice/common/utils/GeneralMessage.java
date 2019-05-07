package com.oliiyu.userservice.common.utils;

public class GeneralMessage {

    private int retCode;

    private String retMessage;

    private Object data;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public GeneralMessage(int retCode, String retMessage, Object data) {
        this.retCode = retCode;
        this.retMessage = retMessage;
        this.data = data;
    }
}
