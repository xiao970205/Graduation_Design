 package com.hibernate.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * Run
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class Run {

     /**
      * 存车方法
      * @param userId   用户id，字符串
      * @param carId    车辆id，字符串
      * @param outTime  取车时间，Date类型
      */
     public void saveCar(String userId,String carId,Date outTime){
         
         //获得存车数据
         Parking parking = new Parking();
         ParkingRun parkingRun = new ParkingRun();
         SpaceRun spaceRun = new SpaceRun();
         //获得入口id
         String inSpaceId = spaceRun.getCrk("入口").getId();
         Date inTime = new Date();
         ContrastRun contrastRun = new ContrastRun();
         String nature = contrastRun.getContrastByRealName("存车中").getId();
         Space fetureSpace = spaceRun.getSaveSpace();
         String fetureSpaceId = fetureSpace.getId();
         fetureSpace.setCarId(carId);
         //锁定空间
         spaceRun.Update(fetureSpace);
         
         
         parking.setId(getId());
         parking.setUserId(userId);
         parking.setCarId(carId);
         parking.setOrginalSpaceId(inSpaceId);
         parking.setNowSpaceId(inSpaceId);
         parking.setFetureSpaceId(fetureSpaceId);
         parking.setInTime(inTime);
         parking.setOutTime(outTime);
         parking.setNature(nature);
         
         parkingRun.save(parking);
     }
     
     public void getCar(){
         ContrastRun contrastRun = new ContrastRun();
         String saveCarRealNameId = contrastRun.getContrastByRealName("存车中").getId();
         String inCarRealNameId = contrastRun.getContrastByRealName("停车中").getId();
         String takeOutCarRealNameId = contrastRun.getContrastByRealName("取车中").getId();
         
         ParkingRun parkingRun = new ParkingRun();
         List<Parking> saveCar = parkingRun.getParkingsByNature(saveCarRealNameId);
         List<Parking> inCar = parkingRun.getParkingsByNature(inCarRealNameId);
         List<Parking> takeOutCar = parkingRun.getParkingsByNature(takeOutCarRealNameId);
     }
     
     /**
      * 分两种情况
      * 正常取车
      * 用户强制取车
      * @param carId
      * @return
      */
     public Parking takeOutCar(String carId){
         ParkingRun parkingRun = new ParkingRun();
         SpaceRun spaceRun = new SpaceRun();
         Parking parking = parkingRun.getParkingByCarid(carId);
         String fetrueSpaceId = spaceRun.getCrk("取车中").getId();
         if(fetrueSpaceId.equals(parking.getNature())){
             return parking;
         }
         Date outTime = parking.getOutTime();
         if(outTime == null){
             outTime = new Date();
         }
         parking.setOutTime(outTime);
         parking.setFetureSpaceId(fetrueSpaceId);
         parking.setOrginalSpaceId(parking.getNowSpaceId());
         
         parkingRun.Update(parking);
         
         return parking;
     }
     
     /**
      * 获得自生成id
      * @return
      */
     public String getId(){
         UUID uuid = UUID.randomUUID();
         return uuid.toString().replace("-", "");
     }
}
