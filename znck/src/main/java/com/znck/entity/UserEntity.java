package com.znck.entity;

public class UserEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nickName;
    private String userName;

    public UserEntity() {
        super();
    }

    public UserEntity(String id, String nickName, String userName) {
        super();
        this.id = id;
        this.nickName = nickName;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public String toString(){
        return "id: " + this.id + " ,UserName: "+ this.userName + " ,NickName: " +this.nickName;
    }
}
