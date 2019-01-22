package com.znck.entity;

public class UserEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nickName;
    private String phone;
    private String realName;
    private String password;
    private String email;
    private String idCard;
    private String nature;

    public UserEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UserEntity(String id, String nickName, String userName, String phone, String realName, String password,
        String email, String idCard, String nature) {
        super();
        this.id = id;
        this.nickName = nickName;
        this.phone = phone;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.idCard = idCard;
        this.nature = nature;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ,NickName: " + this.nickName + ",phone:" + this.phone + ",realName:" + this.realName
            + ",password:" + this.password + ",email" + this.email + ",idCard:" + this.idCard + ",nature:"
            + this.nature;
    }
}
