package com.znck.entity;

public class BaseBean<T> {
    /**
     * 响应码
     */
    private String returnCode;

    private boolean ack;

    private String returnDesc;

    private T returnContent;

    public BaseBean() {
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public T getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(T returnContent) {
        this.returnContent = returnContent;
    }
}
