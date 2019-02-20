package com.znck.entity;

public class PhoneActiveEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String code;

    public PhoneActiveEntity() {
        super();
    }

    public PhoneActiveEntity(String id, String userId, String code) {
        super();
        this.id = id;
        this.userId = userId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "id:" + this.id + " ,userId:+" + this.userId + " ,code:+" + this.code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
