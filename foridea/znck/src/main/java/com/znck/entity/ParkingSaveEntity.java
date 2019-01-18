package com.znck.entity;

import java.util.Date;

public class ParkingSaveEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String carId;
    private String way;
    private Date inTime;
    private Date saveInPlaceTime;
    private Date outTime;
    private Date outInPlaceTime;
    private String saveSpaceId;
    
    public ParkingSaveEntity(){
        super();
    }
    
    public ParkingSaveEntity(String id, String userId,String carId
        ,String way,Date inTime,Date saveInPlaceTime,Date outTime
        ,Date OutInPlaceTime,String saveSpaceId){
        super();
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.way = way;
        this.inTime = inTime;
        this.saveInPlaceTime = saveInPlaceTime;
        this.outTime = outTime;
        this.outInPlaceTime = OutInPlaceTime;
        this.saveSpaceId = saveSpaceId;
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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getSaveInPlaceTime() {
        return saveInPlaceTime;
    }

    public void setSaveInPlaceTime(Date saveInPlaceTime) {
        this.saveInPlaceTime = saveInPlaceTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getOutInPlaceTime() {
        return outInPlaceTime;
    }

    public void setOutInPlaceTime(Date outInPlaceTime) {
        this.outInPlaceTime = outInPlaceTime;
    }

    public String getSaveSpaceId() {
        return saveSpaceId;
    }

    public void setSaveSpaceId(String saveSpaceId) {
        this.saveSpaceId = saveSpaceId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
