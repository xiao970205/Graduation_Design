package com.znck.entity;

/***
 * 汽车实体类
 *
 * @author 肖舒翔
 * @date 2019/1/27
 */
public class CarEntity {

    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String carCard;
    private String carName;
    private String carInfo;
    private String nature;
    
    public CarEntity(){
        super();
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public CarEntity(String id,String userId,String carCard,String carName,String carInfo){
        super();
        this.id = id;
        this.userId = userId;
        this.carCard = carCard;
        this.carName = carName;
        this.carInfo = carInfo;
    }
    
    @Override
    public String toString(){
        return "id:"+this.id+", userId:"+userId+", carCard:"+carCard+", carName:"+carName+", carInfo:"+carInfo;
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

    public String getCarCard() {
        return carCard;
    }

    public void setCarCard(String carCard) {
        this.carCard = carCard;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
    
    
}
