package com.hibernate.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "PARKING")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Parking {

    private String id;
    private String userId;
    private String carId;
    private String orginalSpace;
    private String nowSpace;
    private String fetureSpace;
    private Date inTime;
    private Date outTime;
    private String Way;

    @Id
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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getOrginalSpace() {
        return orginalSpace;
    }

    public void setOrginalSpace(String orginalSpace) {
        this.orginalSpace = orginalSpace;
    }

    public String getNowSpace() {
        return nowSpace;
    }

    public void setNowSpace(String nowSpace) {
        this.nowSpace = nowSpace;
    }

    public String getFetureSpace() {
        return fetureSpace;
    }

    public void setFetureSpace(String fetureSpace) {
        this.fetureSpace = fetureSpace;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getWay() {
        return Way;
    }

    public void setWay(String way) {
        Way = way;
    }
}
