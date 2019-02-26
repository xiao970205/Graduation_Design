package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.EmailActiveEntity;
import com.znck.entity.MailUtil;
import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.PhoneActiveEntity;
import com.znck.entity.SpaceEntity;
import com.znck.entity.UserEntity;
import com.znck.entity.VipEntity;
import com.znck.enums.InitDataListener;

/**
 * 
 * AllService
 * 
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

    @Autowired
    private EmailActiveServiceImpl emailActiveServiceImpl;

    @Autowired
    private PhoneActiveServiceImpl phoneActiveServiceImpl;

    @Autowired
    private VipServiceImpl vipActiveServiceImpl;

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
        if (data.getNickName() != null) {
            // vip
            if (data.getRealName() != null) {
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }

        // 获得存车数据
        ParkingEntity parking = new ParkingEntity();

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

    public void parkingGetCar(UserEntity data) throws ParseException {
        Date outTime = null;
        String carId = data.getId();
        if (data.getNickName() != null) {
            // vip
            if (data.getRealName() != null) {
                Random r = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ParsePosition pos = new ParsePosition(0);
                outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
            }
        }

        takeOutCar(carId, outTime);
    }

    /**
     * 分两种情况 正常取车 用户强制取车
     * 
     * @param carId
     * @return
     * @throws ParseException
     */
    public ParkingEntity takeOutCar(String carId, Date outTimeByWeb) throws ParseException {
        this.setAllContrast();
        ParkingEntity parking = parkingServiceImpl.getParkingByCarid(carId);

        String fetureSpaceId = spaceServiceImpl.getCrk(this.getCk().getRealName()).getId();

        Date outTime = parking.getOutTime();

        if (outTimeByWeb == null) {
            // 正常取车
            if (outTime == null) {
                outTime = getDate();
            }
        } else {
            // 预约取车
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
            user.setNickName("新用户" + phone);
            user.setPassword("123456");
            user.setPhone(phone);
            user.setPhoneNature("0");
            user.setEmailNature("0");
            user.setNature("0");
            userServiceImpl.insert(user);
            user = userServiceImpl.getUserByPhone(phone);
            contrast.setId("true");
            EmailActiveEntity emailActiveEntity = new EmailActiveEntity(getId(), user.getId());
            emailActiveServiceImpl.insert(emailActiveEntity);
        }
        return contrast;
    }

    public UserEntity landing(UserEntity massage) {
        return userServiceImpl.findByUserNameAndPassword(massage.getPhone(), massage.getPassword());
    }

    public UserEntity getUserByPhone(UserEntity data) {
        return userServiceImpl.getUserByPhone(data.getPhone());
    }

    public String changePhone(UserEntity data) {
        UserEntity user = userServiceImpl.getUserByPhone(data.getId());
        user.setPhone(data.getPhone());
        user.setPhoneNature("0");
        user = changeUserNature(user);
        userServiceImpl.update(user);
        return "true";
    }

    public String changeEmail(UserEntity data) {
        UserEntity user = userServiceImpl.getUserByPhone(data.getId());
        user.setEmail(data.getEmail());
        user.setEmailNature("0");
        user = changeUserNature(user);
        userServiceImpl.update(user);
        return "true";
    }

    public String changeSensitiveMessage(UserEntity data) {
        UserEntity user = userServiceImpl.getUserByPhone(data.getId());
        user.setRealName(data.getRealName());
        user.setIdCard(data.getId());
        user = changeUserNature(user);
        userServiceImpl.update(user);
        return "true";
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public List<CarEntity> getCardByUserId(ContrastEntity data) {
        List<CarEntity> allCarByUserIdWithOutNature = carServiceImpl.getCarsHaveNatureByUserId(data.getId());
        List<CarEntity> allCarByUserId = new ArrayList<CarEntity>();
        allCarByUserIdWithOutNature.forEach(car ->{
            CarEntity carWithNature = car;
            List<ParkingEntity> parkings = InitDataListener.parkings.stream().filter(pa ->pa.getCarId().equals(car.getId())).collect(Collectors.toList());
            if(parkings.size() == 0){
                carWithNature.setNature(null);
            }else{
                carWithNature.setNature(contrastServiceImpl.getOne(InitDataListener.parkings.stream().filter(pa ->pa.getCarId().equals(car.getId())).collect(Collectors.toList()).get(0).getNature()).getRealName());
            }
            allCarByUserId.add(carWithNature);
        });
        return allCarByUserId;
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
        VipEntity vip = vipActiveServiceImpl.getVipByUserId(user.getId());
        if (vip == null) {
            Calendar cal = Calendar.getInstance();
            Date date = new Date();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            vip = new VipEntity(getId(), user.getId(), cal.getTime());
            vipActiveServiceImpl.insert(vip);
        } else {
            Date date = vip.getEndDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            date = cal.getTime();
            vip.setEndDate(date);
            vipActiveServiceImpl.update(vip);
        }
        final int sizeOne = 1;
        if (user.getNature().equals(sizeOne)) {
            user.setNature("2");
        }
        userServiceImpl.update(user);
        return user;
    }

    public ContrastEntity sendEmailForActive(UserEntity data) {
        ContrastEntity contrast = new ContrastEntity();
        if (userServiceImpl.getUserByEmail(data.getEmail()).size() == 0) {
            contrast.setRealName("true");
            UserEntity user = userServiceImpl.getUserByPhone(data.getPhone());
            user.setEmail(data.getEmail());
            userServiceImpl.update(user);
            EmailActiveEntity emailActiveEntity = emailActiveServiceImpl.getEmailActiveByUserId(user.getId());
            System.out.println(data.getEmail() + "\n" + emailActiveEntity.getId());
            MailUtil main = new MailUtil(data.getEmail(), emailActiveEntity.getId());
            main.run();
        } else {
            contrast.setRealName("false");
        }
        return contrast;
    }

    public void activeEmail(String code, String email) {
        EmailActiveEntity emailActiveEntity = emailActiveServiceImpl.getOne(code);
        UserEntity user = userServiceImpl.getOne(emailActiveEntity.getUserId());
        user.setEmail(email);
        user.setEmailNature("1");
        user = changeUserNature(user);
        userServiceImpl.update(user);
        emailActiveServiceImpl.delete(code);
    }

    public void sendVerificationCode(UserEntity user) {
        String phone = user.getPhone();
        int code = (int)((Math.random() * 9 + 1) * 1000);
        user = userServiceImpl.getUserByPhone(phone);
        PhoneActiveEntity phoneActive = new PhoneActiveEntity(getId(), user.getId(), code + "");
        phoneActiveServiceImpl.insert(phoneActive);
        // 需要发送验证码接口
    }

    public String activeVerificationCode(UserEntity data) {
        // TODO Auto-generated method stub
        String code = data.getId();
        String phone = data.getPhone();
        UserEntity user = userServiceImpl.getUserByPhone(phone);
        List<PhoneActiveEntity> codes = phoneActiveServiceImpl.getPhoneActiveByUserPhone(phone);
        for (PhoneActiveEntity cod : codes) {
            if (cod.getCode().equals(code)) {
                phoneActiveServiceImpl.deleteByUserId(user.getId());
                user.setPhoneNature("1");
                user = changeUserNature(user);
                userServiceImpl.update(user);
                return "true";
            }
        }
        return "false";
    }

    public UserEntity changeUserNature(UserEntity user) {
        final int sizeZero = 0;
        final int sizeOne = 0;
        user.setNature("1");
        if (StringUtils.isNullOrEmpty(user.getRealName())) {
            user.setNature("0");
        }
        if (StringUtils.isNullOrEmpty(user.getIdCard())) {
            user.setNature("0");
        }
        if (user.getEmailNature().equals(sizeZero)) {
            user.setNature("0");
        }
        if (user.getPhoneNature().equals(sizeZero)) {
            user.setNature("0");
        }
        VipEntity vip = vipActiveServiceImpl.getVipByUserId(user.getId());
        if (user.getNature().equals(sizeOne) & vip != null) {
            user.setNature("2");
        }
        return user;
    }

    public void addUserSensitiveInfo(UserEntity user) {
        UserEntity oldUser = userServiceImpl.getUserByPhone(user.getPhone());
        oldUser.setRealName(user.getRealName());
        oldUser.setIdCard(user.getIdCard());
        oldUser = changeUserNature(oldUser);
        userServiceImpl.update(oldUser);
    }

    public String changeGeneralInfo(UserEntity user) {
        UserEntity oldUser = userServiceImpl.getUserByPhone(user.getPhone());
        oldUser.setNickName(user.getNickName());
        userServiceImpl.update(oldUser);
        return "true";
    }

    public String changePassword(UserEntity data) {
        // TODO Auto-generated method stub
        UserEntity user = userServiceImpl.getUserByPhone(data.getPhone());
        user.setPassword(data.getPassword());
        userServiceImpl.update(user);
        return "true";
    }

    public void endVip() {
        vipActiveServiceImpl.deleteNowBiggerEndDate();
    }
}
