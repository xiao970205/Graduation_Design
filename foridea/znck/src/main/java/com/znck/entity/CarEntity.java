package com.znck.entity;

public class CarEntity {

    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String carInfo;

    public CarEntity(){
        super();
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public CarEntity(String id,String userId,String carInfo){
        super();
        this.id = id;
        this.userId = userId;
        this.carInfo = carInfo;
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

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
    
    @Override
    public String toString(){
        return "id: " + this.id + " ,UserId: "+ this.userId + " ,CarInfo: " +this.carInfo;
    }
}
