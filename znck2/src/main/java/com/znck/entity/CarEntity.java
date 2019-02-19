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

    public void encrypt(String password) {
        this.id = AESUtil.encrypt(id, password);
        this.id = AESUtil.encrypt(userId, password);
        this.id = AESUtil.encrypt(carCard, password);
        this.id = AESUtil.encrypt(carName, password);
        this.id = AESUtil.encrypt(carInfo, password);
        this.id = AESUtil.encrypt(nature, password);
    }

    public void decrypt(String password) {
        this.id = AESUtil.decrypt(id, password);
        this.id = AESUtil.decrypt(userId, password);
        this.id = AESUtil.decrypt(carCard, password);
        this.id = AESUtil.decrypt(carName, password);
        this.id = AESUtil.decrypt(carInfo, password);
        this.id = AESUtil.decrypt(nature, password);
    }

    public CarEntity() {
        super();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public CarEntity(String id, String userId, String carCard, String carName, String carInfo) {
        super();
        this.id = id;
        this.userId = userId;
        this.carCard = carCard;
        this.carName = carName;
        this.carInfo = carInfo;
    }

    @Override
    public String toString() {
        return "id:" + this.id + ", userId:" + userId + ", carCard:" + carCard + ", carName:" + carName + ", carInfo:"
            + carInfo;
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
