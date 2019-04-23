package com.znck.service;

import com.sun.org.apache.xml.internal.security.Init;
import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.PublicMethods;
import com.znck.enums.InitDataListener;

import javax.naming.ldap.InitialLdapContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 肖舒翔
 */
public class AllAdminService {
    private ContrastServiceImpl contrastService;

    /**
     * 获得当前等待用户停车中的数量
     * @return
     */
    private int getWaitForSaveCar(){
        return InitDataListener.parkings.stream()
                .filter(parking ->parking.getNature()
                        .equals(contrastService.getContrastByRealName("等待用户停车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获得普通用户开往停车位的数据
     * @return
     */
    private int getNoramalRunToSavePlace(){
        return InitDataListener.parkings.stream()
                .filter(parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("停车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获得vip用户开往停车位的数据
     * @return
     */
    private int getVipRunToSavePlace(){
        return InitDataListener.parkings.stream()
                .filter(parkingEntity -> parkingEntity.getNature()
                        .equals(contrastService.getContrastByRealName("vip停车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 车辆中存车的数量
     * @return
     */
    private int getSaving(){
        return InitDataListener.parkings.stream()
                .filter(parkingEntity -> parkingEntity.getNature()
                        .equals(contrastService.getContrastByRealName("存车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获得普通用户取车数量
     * @return
     */
    private int getNormalGettingCar(){
        return InitDataListener.parkings.stream()
                .filter(parkingEntity -> parkingEntity.getNature()
                        .equals(contrastService.getContrastByRealName("取车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获得vip用户取车数量
     * @return
     */
    private int getVipGettingCar(){
        return InitDataListener.parkings.stream()
                .filter(parkingEntity -> parkingEntity.getNature()
                        .equals(contrastService.getContrastByRealName("vip取车中")))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获得车库中的剩余
     * @return
     */
    private int getSpaceCanSaveNumber(){
        return InitDataListener.spaces.stream().filter(
                spaceEntity ->
                        spaceEntity.getNature().equals(contrastService.getContrastByRealName("车库-空置"))
        ).collect(Collectors.toList()).size();
    }

    /**
     * 获得车库中的占用
     * @return
     */
    private int getSpaceCanNotSaveNumber(){
        return InitDataListener.spaces.stream().filter(
                spaceEntity ->
                        spaceEntity.getNature().equals(contrastService.getContrastByRealName("车库-占用"))
        ).collect(Collectors.toList()).size();
    }

    /**
     * 本日车辆时段停车数量,分区24
     */
    private void getBrClSdTcSl(List<ParkingSaveEntity> parkingSaveEntityList) {
        List<ParkingSaveEntity> toDaySave = parkingSaveEntityList.stream().filter(
                parkingSaveEntity -> {
                    try {
                        return pdJr(parkingSaveEntity.getInPlaceTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
        ).collect(Collectors.toList());
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            data.add(0);
        }
        parkingSaveEntityList.forEach(parkingSaveEntity -> {
            String hours = getHour(parkingSaveEntity.getInPlaceTime());
            data.set(Integer.parseInt(hours), (data.get(Integer.parseInt(hours)) + 1));
        });
    }

    /**
     * 本月车辆时段停车数量,分区24
     */
    private void getByClSdTcSl(List<ParkingSaveEntity> parkingSaveEntityList) {
        List<ParkingSaveEntity> toDaySave = parkingSaveEntityList.stream().filter(
                parkingSaveEntity -> {
                    try {
                        return pdsbsJn(parkingSaveEntity.getInPlaceTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
        ).collect(Collectors.toList());
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            data.add(0);
        }
        parkingSaveEntityList.forEach(parkingSaveEntity -> {
            String hours = getHour(parkingSaveEntity.getInPlaceTime());
            data.set(Integer.parseInt(hours), (data.get(Integer.parseInt(hours)) + 1));
        });
    }

    /**
     * 得到现在小时
     */
    private String getHour(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }


    /**
     * 判断是不是今天
     */
    private boolean pdJr(Date date) throws ParseException {
        Date now = PublicMethods.getDate();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sf.format(now);
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 判断是不是今年的同一月
     * @param date
     * @return
     */
    private boolean pdsbsJn(Date date) throws ParseException {
        Date now = PublicMethods.getDate();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sf.format(now).substring(0,6);
        String day = sf.format(date).substring(0,6);
        return day.equals(nowDay);
    }
}
