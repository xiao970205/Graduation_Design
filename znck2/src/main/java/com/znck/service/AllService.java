package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.SpaceEntity;
import com.znck.entity.UserEntity;

/**
 * 
 * AllService
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class AllService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CarServiceImpl carServiceImpl;
    
    @Autowired
    private ContrastServiceImpl contrastServiceImpl;

    @Autowired
    private ParkingServiceImpl parkingServiceImpl;

    @Autowired
    private SpaceServiceImpl spaceServiceImpl;

    @Autowired
    private ParkingSaveServiceImpl parkingSaveServiceImpl;

    /**
     * id:729352f0e3b74fee91bf15baa7187e58 realName:出口
     */
    private ContrastEntity ck;

    /**
     * id:7bd42e7aad7645e4ad586349691a87e6 realName:车库-占用
     */
    private ContrastEntity ckZy;

    /**
     * id:1e95f2208c7f4dd9b584889bba7e3164 realName:入口
     */
    private ContrastEntity rk;

    /**
     * id:85f8320ee6004d5eb7f21a470fadb366 realName:取车中
     */
    private ContrastEntity qCz;

    /**
     * id:351cc2cdda6547528e20c6444e4a3bbd realName:存车中
     */
    private ContrastEntity cCc;
    
    
    /**
     * 存车方法
     * 
     * @param data ContrastEntity类型数据，id是carid，realName为时间，Date类型
     * @throws ParseException
     */
    public void saveCar(UserEntity data) throws ParseException {

        this.setAllContrast();
        Date outTime = null;
        if(data.getNickName()!=null){
            //vip
            if(data.getRealName()!=null){
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }
        
        // 获得存车数据
        ParkingEntity parking = new ParkingEntity();

        // 获得入口id
        SpaceEntity inSpaceId = spaceServiceImpl.getCrk(this.getRk().getRealName());
        Date inTime = getDate();

        String nature = this.getcCc().getId();

        SpaceEntity fetureSpace = spaceServiceImpl.getSaveSpace().get(0);
        String fetureSpaceId = fetureSpace.getId();

        fetureSpace.setNature(this.getCkZy().getId());
        // 锁定空间
        spaceServiceImpl.update(fetureSpace);

        parking.setId(getId());
        parking.setCarId(data.getId());
        parking.setNowSpaceId(inSpaceId.getId());
        parking.setFetureSpaceId(fetureSpaceId);
        parking.setInTime(inTime);
        parking.setOutTime(outTime);
        parking.setNature(nature);
        parking.setWay(inSpaceId.getX() + "," + inSpaceId.getY() + "," + inSpaceId.getZ());
        
        ParkingSaveEntity parkingSave = new ParkingSaveEntity();
        parkingSave.setId(parking.getId());
        parkingSave.setCarId(parking.getCarId());
        parkingSave.setInTime(parking.getInTime());
        parkingSave.setOutTime(parking.getOutTime());
        parkingSave.setSaveSpaceId(parking.getFetureSpaceId());
        parkingSaveServiceImpl.insert(parkingSave);
        
        parkingServiceImpl.insert(parking);
    }    
  
    public Date getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        return date2;
    }
    
    public void setAllContrast() {
        this.setCkZy(contrastServiceImpl.getContrastByRealName("车库-占用"));
        this.setRk(contrastServiceImpl.getContrastByRealName("入口"));
        this.setqCz(contrastServiceImpl.getContrastByRealName("取车中"));
        this.setcCc(contrastServiceImpl.getContrastByRealName("存车中"));
        this.setCk(contrastServiceImpl.getContrastByRealName("出口"));
    }
    
    public void parkingGetCar(UserEntity data) throws ParseException{
        Date outTime = null;
        String carId = data.getId();
        if(data.getNickName()!=null){
            //vip
            if(data.getRealName()!=null){
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }
        
        takeOutCar(carId,outTime);
    }
    
    /**
     * 分两种情况 正常取车 用户强制取车
     * 
     * @param carId
     * @return
     * @throws ParseException
     */
    public ParkingEntity takeOutCar(String carId,Date outTimeByWeb) throws ParseException {
        this.setAllContrast();

        ParkingEntity parking = parkingServiceImpl.getParkingByCarid(carId);

        String fetureSpaceId = spaceServiceImpl.getCrk(this.getCk().getRealName()).getId();

        Date outTime = parking.getOutTime();
        
        if(outTimeByWeb == null){
            //正常取车
            if (outTime == null) {
                outTime = getDate();
            }
        }else{
            //预约取车
            outTime = outTimeByWeb;
        }
        parking.setOutTime(outTime);
        parking.setFetureSpaceId(fetureSpaceId);

        parking.setNature(this.getqCz().getId());

        parkingServiceImpl.update(parking);

        ParkingSaveEntity parkingSave = parkingSaveServiceImpl.getOne(parking.getId());
        parkingSave.setOutTime(parking.getOutTime());
        parkingSaveServiceImpl.update(parkingSave);
        return parking;
    }
    
    public UserEntity changeUserInfo(UserEntity userEntity) {
        UserEntity userOlder = null;
        final String  value1 = "1";
        final String  value3 = "3";
        final Integer numberThree = 3;
        if (value1.equals(userEntity.getId())) {
            userOlder = userServiceImpl.getUserByPhone(userEntity.getPhone());
            // 一般信息
            userOlder.setNickName(userEntity.getNickName());
        } else if (userEntity.getId().equals(value3)) {
            // 敏感信息
            userOlder = userServiceImpl.getUserByPhone(userEntity.getPhone());
            int size = 0;
            if (!StringUtils.isNullOrEmpty(userEntity.getRealName())) {
                userOlder.setRealName(userEntity.getRealName());
                size++;
            }
            if (!StringUtils.isNullOrEmpty(userEntity.getPassword())) {
                userOlder.setPassword(userEntity.getPassword());
            } else {
                userOlder.setPassword("123456");
            }
            if (!StringUtils.isEmptyOrWhitespaceOnly(userEntity.getEmail())) {
                userOlder.setEmail(userEntity.getEmail());
                size++;
            }
            if (!StringUtils.isEmptyOrWhitespaceOnly(userEntity.getIdCard())) {
                userOlder.setIdCard(userEntity.getIdCard());
                size++;
            }

            if (size == numberThree) {
                userOlder.setNature("1");
            } else {
                userOlder.setNature("0");
            }
        } else {
            // 修改电话
            userOlder = userServiceImpl.getUserByPhone(userEntity.getId());
            userOlder.setPhone(userEntity.getPhone());
        }
        userServiceImpl.update(userOlder);
        return userOlder;
    }

    public ContrastEntity regist(UserEntity data) {
        String phone = data.getPhone();
        ContrastEntity contrast = new ContrastEntity();
        if (userServiceImpl.getUserByPhone(phone) != null) {
            // 已注册
            contrast.setId("false");
        } else {
            // 未注册
            UserEntity user = new UserEntity();
            user.setId(getId());
            user.setPhone(phone);
            user.setNickName("新用户" + phone);
            user.setNature("0");
            user.setPassword("123456");
            userServiceImpl.insert(user);
            user = userServiceImpl.getUserByPhone(phone);
            contrast.setId("true");
        }
        return contrast;
    }

    public UserEntity landing(UserEntity massage) {
        System.out.println("allservice2");
        return userServiceImpl.findByUserNameAndPassword(massage.getPhone(), massage.getPassword());
    }

    public UserEntity getUserByPhone(UserEntity data) {
        return userServiceImpl.getUserByPhone(data.getPhone());
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public List<CarEntity> getCardByUserId(ContrastEntity data) {
        return carServiceImpl.getCarsHaveNatureByUserId(data.getId());
    }

    public CarEntity getCarById(CarEntity data) {
        return carServiceImpl.getOne(data.getId());
    }

    public void deleteCarById(CarEntity data) {
        carServiceImpl.delete(data.getId());
    }

    public void saveNewCarByUserPhone(CarEntity carEntity) {
        carEntity.setUserId(userServiceImpl.getUserByPhone(carEntity.getId()).getId());
        carEntity.setId(getId());
        carServiceImpl.insert(carEntity);
    }
    
    public void updateCarWithoutUserId(CarEntity car) {
        CarEntity oldCar = carServiceImpl.getOne(car.getId());
        car.setId(oldCar.getId());
        car.setUserId(oldCar.getUserId());
        this.carServiceImpl.update(car);
    }
    
    public ContrastEntity getCkZy() {
        return ckZy;
    }

    public void setCkZy(ContrastEntity ckZy) {
        this.ckZy = ckZy;
    }

    public ContrastEntity getRk() {
        return rk;
    }

    public void setRk(ContrastEntity rk) {
        this.rk = rk;
    }

    public ContrastEntity getqCz() {
        return qCz;
    }

    public void setqCz(ContrastEntity qCz) {
        this.qCz = qCz;
    }

    public ContrastEntity getcCc() {
        return cCc;
    }

    public void setcCc(ContrastEntity cCc) {
        this.cCc = cCc;
    }

    public ContrastEntity getCk() {
        return ck;
    }

    public void setCk(ContrastEntity ck) {
        this.ck = ck;
    }

    public UserEntity toBeVip(UserEntity data) {
        // TODO Auto-generated method stub
         UserEntity user = userServiceImpl.getUserByPhone(data.getPhone());
         user.setNature("2");
         userServiceImpl.update(user);
         return null;
    }
}
