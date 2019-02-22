package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;
import com.znck.entity.UserEntity;
import com.znck.enums.InitDataListener;

@Service
public class AllparkingService {
    @Autowired
    private ContrastServiceImpl contrastServiceImpl;
    
    public void onclock(String threadId) throws InterruptedException{
        while(!(InitDataListener.lockForParking.equals(threadId) & InitDataListener.lockForSpace.equals(threadId))){
            if(StringUtils.isNullOrEmpty(InitDataListener.lockForParking)){
                InitDataListener.lockForParking = threadId;
            }
            if(StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)){
                InitDataListener.lockForSpace = threadId;
            }
            Thread.sleep(100);
        }
    }
    
    public void offclock(String threadId){
        InitDataListener.lockForParking = null;
        InitDataListener.lockForSpace = null;
    }
    
    /**
     * 存车方法
     * 
     * @param data ContrastEntity类型数据，id是carid，realName为时间，Date类型
     * @return
     * @throws ParseException
     * @throws InterruptedException 
     */
    public String saveCarByStatic(UserEntity data) throws ParseException, InterruptedException {
        String threadId = getId();
        onclock(threadId);
        
        List<ParkingEntity> parkings = InitDataListener.parkings;
        List<SpaceEntity> spaces = InitDataListener.spaces;
        // 处理日期，若日期不为空则格式化日期
        Date outTime = null;
        if (!StringUtils.isNullOrEmpty(data.getNickName())) {
            // vip
            if (!StringUtils.isNullOrEmpty(data.getRealName())) {
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }

        // 获得入口id
        SpaceEntity inSpaceId = spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
            .filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName("入口").getId()))
            .collect(Collectors.toList()).get(0);

        // 获得车位，若没有则返回字符串false
        List<SpaceEntity> spacesForFeture = spaces.stream()
            .filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName("车库-空置").getId()))
            .sorted(Comparator.comparing(SpaceEntity::getWeight)).collect(Collectors.toList());
        if (spacesForFeture.size() == 0) {
            return "false";
        }

        // 获得车位
        SpaceEntity fetureSpace = spacesForFeture.get(0);

        // 更新空间列表，锁定空间
        SpaceEntity oldSpace = spacesForFeture.get(0);
        fetureSpace.setNature(contrastServiceImpl.getContrastByRealName("车库-占用").getId());
        Collections.replaceAll(spaces, oldSpace, fetureSpace);

        // 向停车列表插入数据
        ParkingEntity parking = new ParkingEntity(getId(), data.getId(), inSpaceId.getId(), fetureSpace.getId(),
            getDate(), outTime, contrastServiceImpl.getContrastByRealName("存车中").getId(),
            inSpaceId.getX() + "," + inSpaceId.getY() + "," + inSpaceId.getZ(), fetureSpace.getId());
        parkings.add(parking);
        InitDataListener.parkings = parkings;
        InitDataListener.spaces = spaces;
        
        offclock(threadId);
        return "true";
    }
    
    /**
     * 取车方法
     * 
     * @param data
     * @return
     * @throws ParseException
     * @throws InterruptedException 
     */
    public String parkingGetCarByStatic(UserEntity data) throws ParseException, InterruptedException {
        String threadId = getId();
        onclock(threadId);
        
        List<ParkingEntity> parkings = InitDataListener.parkings;
        List<SpaceEntity> spaces = InitDataListener.spaces;

        Date outTimeByWeb = null;
        String carId = data.getId();
        if (data.getNickName() != null) {
            // vip
            if (data.getRealName() != null) {
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTimeByWeb = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }

        ParkingEntity parking
            = parkings.stream().filter(a -> a.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
        ParkingEntity oldParking
            = parkings.stream().filter(a -> a.getCarId().equals(carId)).collect(Collectors.toList()).get(0);

        String fetureSpaceId
            = spaces.stream().filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName("出口").getId()))
                .collect(Collectors.toList()).get(0).getId();

        Date outTime = parking.getOutTime();

        if (outTimeByWeb != null) {
            outTime = outTimeByWeb;
        } else if (outTime == null) {
            outTime = getDate();
        } else {

        }

        parking.setOutTime(outTime);
        parking.setFetureSpaceId(fetureSpaceId);
        parking.setNature(contrastServiceImpl.getContrastByRealName("取车中").getId());

        Collections.replaceAll(parkings, oldParking, parking);
        InitDataListener.parkings = parkings;
        
        offclock(threadId);
        return null;
    }
    
    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
    
    public Date getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        return date2;
    }
    
    public void getCar(){
        System.out.println("运行中");
    }
}
