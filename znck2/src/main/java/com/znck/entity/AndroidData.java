package com.znck.entity;

import java.util.List;

public class AndroidData {

    /**
     * 用户认证信息（用户id+'|'+作用内日期+'|'+过期日期）
     */
    private String userRZ;

    /**
     * 用于弹窗信息
     */
    private String showInfo;

    /**
     * 是否执行好方法(true&false)
     */
    private String operation;

    /**
     * 传输的信息（userEntity类型）
     */
    private UserEntity dataByUser;

    /**
     * 传输的信息（链表carEntity)
     */
    private List<CarEntity> dataByCars;

    public String getUserRZ() {
        return userRZ;
    }

    public void setUserRZ(String userRZ) {
        this.userRZ = userRZ;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public UserEntity getDataByUser() {
        return dataByUser;
    }

    public void setDataByUser(UserEntity dataByUser) {
        this.dataByUser = dataByUser;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<CarEntity> getDataByCars() {
        return dataByCars;
    }

    public void setDataByCars(List<CarEntity> dataByCars) {
        this.dataByCars = dataByCars;
    }
}
