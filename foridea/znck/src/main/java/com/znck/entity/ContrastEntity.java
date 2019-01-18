package com.znck.entity;

public class ContrastEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String realName;
    private String fId;

    public ContrastEntity() {
        super();
    }

    public ContrastEntity(String id, String realName, String fId) {
        super();
        this.id = id;
        this.realName = realName;
        this.fId = fId;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ,RealName:" + this.realName + " ,fId:" + this.fId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
