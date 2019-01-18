package com.znck.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String carId;
    private String orginalSpaceId;
    private String nowSpaceId;
    private String fetureSpaceId;
    private Date inTime;
    private Date saveInPlaceTime;
    private Date outTime;
    private String nature;
    private String way;

    public ParkingEntity(){
        super();
    }
    
    public ParkingEntity(String id,String userId,String carId,
        String orginalSpaceId,String nowSpaceId,String fetureSpaceId,
        Date inTime,Date outTime,String nature){
        super();
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.orginalSpaceId = orginalSpaceId;
        this.nowSpaceId = nowSpaceId;
        this.fetureSpaceId = fetureSpaceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.nature = nature;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String inTime = null;
        String outTime = null;
        if(this.inTime == null){
            inTime = "null";
        }else{
            inTime = format.format(this.inTime);
        }
        if(this.outTime == null){
            outTime = "null";
        }else{
            outTime = format.format(this.inTime);
        }
        return "Id: "+this.id+" ,UserId: "+this.userId+" ,CarId:"+this.carId
            +" ,OrginalSpaceId:"+this.orginalSpaceId+", NowSpaceId:" + this.nowSpaceId
            +" ,inTime:"+inTime+", OutTime:"+outTime
            +" ,nature"+this.nature;
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
}
