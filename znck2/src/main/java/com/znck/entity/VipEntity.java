package com.znck.entity;

import java.util.Date;

public class VipEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private Date endDate;

    public VipEntity(){
        super();
    }
    
    public VipEntity(String id,String userId,Date endDate){
        this.id = id;
        this.userId = userId;
        this.endDate = endDate;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
