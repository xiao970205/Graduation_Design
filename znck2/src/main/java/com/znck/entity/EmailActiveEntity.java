package com.znck.entity;

public class EmailActiveEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;

    public EmailActiveEntity() {
        super();
    }

    public EmailActiveEntity(String id, String userId) {
        super();
        this.id = id;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "id:" + this.id + " ,userId:+" + this.userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
