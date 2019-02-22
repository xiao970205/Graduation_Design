package com.znck.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * ParkingEntity
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class ParkingEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String carId;
    private String nowSpaceId;
    private String fetureSpaceId;
    private Date inTime;
    private Date outTime;
    private String nature;
    private String way;

    private Date saveInPlaceTime;

    private Date outInPlaceTime;

    private String saveSpaceId;

    private String nature2;

    public ParkingEntity() {
        super();
    }

    public int getInTimeSizeInt(){
        return this.inTime.getDate();
    }
    
    public int getOutTimeSizeInt(){
        return this.outTime.getDate();
    }
    
    public ParkingEntity(String id,String carId,String nowSpaceId,String fetureSpaceId,Date inTime,Date outTime,String nature,String way,String saveSpaceId){
        this.id = id;
        this.carId = carId;
        this.nowSpaceId = nowSpaceId;
        this.fetureSpaceId = fetureSpaceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.nature = nature;
        this.way = way;
        this.saveSpaceId = saveSpaceId;
    }
    
    public ParkingEntity(String id, String carId, String nowSpaceId, String fetureSpaceId, Date inTime, Date outTime,
        String nature) {
        super();
        this.id = id;
        this.carId = carId;
        this.nowSpaceId = nowSpaceId;
        this.fetureSpaceId = fetureSpaceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.nature = nature;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String inTime = null;
        String outTime = null;
        if (this.inTime == null) {
            inTime = "null";
        } else {
            inTime = format.format(this.inTime);
        }
        if (this.outTime == null) {
            outTime = "null";
        } else {
            outTime = format.format(this.inTime);
        }
        return "Id: " + this.id + " ,CarId:" + this.carId + ", NowSpaceId:" + this.nowSpaceId + " ,inTime:" + inTime
            + ", OutTime:" + outTime + " ,nature" + this.nature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Date getSaveInPlaceTime() {
        return saveInPlaceTime;
    }

    public void setSaveInPlaceTime(Date saveInPlaceTime) {
        this.saveInPlaceTime = saveInPlaceTime;
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

    public String getNature2() {
        return nature2;
    }

    public void setNature2(String nature2) {
        this.nature2 = nature2;
    }

}
