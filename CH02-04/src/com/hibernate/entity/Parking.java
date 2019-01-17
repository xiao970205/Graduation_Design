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
    private String orginalSpaceId;
    private String nowSpaceId;
    private String fetureSpaceId;
    private Date inTime;
    private Date outTime;
    private String nature;
    private String way;

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

    public String getOrginalSpaceId() {
        return orginalSpaceId;
    }

    public void setOrginalSpaceId(String orginalSpaceId) {
        this.orginalSpaceId = orginalSpaceId;
    }

    public String getNowSpaceId() {
        return nowSpaceId;
    }

    public void setNowSpaceId(String nowSpaceId) {
        this.nowSpaceId = nowSpaceId;
    }

    public String getFetureSpaceId() {
        return fetureSpaceId;
    }

    public void setFetureSpaceId(String fetureSpaceId) {
        this.fetureSpaceId = fetureSpaceId;
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

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
    

}
