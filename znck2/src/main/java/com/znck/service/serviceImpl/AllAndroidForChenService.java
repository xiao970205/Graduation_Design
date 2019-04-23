package com.znck.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.znck.entity.*;
import com.znck.enums.InitDataListener;
import com.znck.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllAndroidForChenService {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @Autowired
    private ContrastServiceImpl contrastServiceImpl;

    @Autowired
    private EmailActiveServiceImpl emailActiveServiceImpl;

    @Autowired
    private PhoneActiveServiceImpl phoneActiveServiceImpl;

    @Autowired
    private VipServiceImpl vipActiveServiceImpl;

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @Autowired
    private ParkingSaveServiceImpl parkingSaveServiceImpl;

    @Autowired
    private AllParkingModle allParkingModle;

    private final static String ENTRANCE = "入口";

    private final static String EXPORT = "出口";

    private final static String GARAGE_TAKE_UP = "车库-占用";

    private final static String GETIN_CAR = "取车中";

    private final static String GARAGE_VACANCY = "车库-空置";

    private final static String IN_THE_PARKING = "停车中";

    private final static String BUFFER_VACANCY = "缓冲区-空置";

    private final static String BUFFER_TAKE_UP = "缓冲区-占用";

    private final static String VIP_WAIT_USER_PARKING = "等待用户停车中";

    private final static String VIP_IN_THE_PARKING = "vip停车中";

    private final static String VIP_APP_GETCAR = "vip预约取车中";

    private final static String VIP_GETCAR = "vip取车中";

    private final static String PUTIN_CAR = "存车中";

    /**
     * 登录验证
     * @param data 通过dataByUser的phone和password两个信息进行登录
     * @return  返回的信息中登录失败则是showInfo中提示错误信息，用户校验码userRz为空，登录成功则是showInfo中提示登录信息,dataByUser中是用户信息，userRz为认证码,operation为操作是否成功(ture/false)
     * @throws ParseException
     */
    public BaseBean landing(BaseBean data) throws ParseException {
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String phone = (String) jsonObject.get("phone");
        String password = (String) jsonObject.get("password");
        UserEntity user = userServiceImpl.findByUserNameAndPassword(phone, password);
        BaseBean BaseBean = new BaseBean();
        if(user == null){
            BaseBean.setReturnDesc("用户名或者密码错误！");
            BaseBean.setReturnCode(null);
            BaseBean.setAck(false);
            return BaseBean;
        }
        BaseBean.setReturnCode(getReturnCode(user.getId()));
        BaseBean.setReturnDesc("登陆成功！");
        BaseBean.setAck(true);
        JSONArray cars = carsToJsonArray(this.getCardByUserId(user.getId()));
        JSONObject jsonObjectForReturn = new JSONObject();
        jsonObjectForReturn.put("user",JSONObject.parseObject(JSON.toJSONString(user)));
        jsonObjectForReturn.put("cars",cars);
        BaseBean.setReturnContent(jsonObject);
        return BaseBean;
    }

    /**
     * 注册，通过dataByUser中的phone进行注册
     * @param data {'phone'}
     * @return  返回信息中的showInfo，无认证码，请从新登录,operation为操作是否成功(ture/false)
     */
    public BaseBean regist(BaseBean data) {
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        BaseBean returnData = new BaseBean();
        String phone = (String) jsonObject.get("phone");
        if (userServiceImpl.getUserByPhone(phone) != null) {
            // 已注册
            returnData.setReturnDesc("用户已注册，请选择其他手机号！");
            returnData.setAck(false);
        } else {
            // 未注册
            UserEntity user = new UserEntity();
            user.setId(PublicMethods.getId());
            user.setNickName("新用户" + phone);
            user.setPassword("123456");
            user.setPhone(phone);
            user.setPhoneNature("0");
            user.setEmailNature("0");
            user.setNature("0");
            userServiceImpl.insert(user);
            user = userServiceImpl.getUserByPhone(phone);
            EmailActiveEntity emailActiveEntity = new EmailActiveEntity(PublicMethods.getId(), user.getId());
            emailActiveServiceImpl.insert(emailActiveEntity);
            returnData.setReturnDesc("用户成功注册！请在主页面登录！");
            returnData.setAck(true);

        }
        return returnData;
    }

    /**
     * 修改电话号码，data的dataByUser的phone为新电话，data的UserRZ为认证信息
     * @param data {'phone'}
     * @return  如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo修改成功，新的用户认证码
     */
    public BaseBean changePhone(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        UserEntity user = userServiceImpl.getOne(data.getReturnCode().split("|")[0]);
        user.setPhone((String) jsonObject.get("phone"));
        user.setPhoneNature("0");
        user = changeUserNature(user);
        userServiceImpl.update(user);
        returnData.setReturnDesc("修改手机号成功！");
        returnData.setAck(true);
        return returnData;
    }

    /**
     * 修改邮箱，data的dataByUser的email为新邮箱，data的UserRz为认证信息
     * @param data {'email'}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo修改成功，新的用户认证码
     * @throws ParseException
     */
    public BaseBean changeEmail(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        UserEntity user = userServiceImpl.getOne(data.getReturnCode().split("|")[0]);
        user.setEmail((String) jsonObject.get("email"));
        user.setEmailNature("0");
        user = changeUserNature(user);
        userServiceImpl.update(user);
        returnData.setReturnDesc("修改邮箱成功！");
        returnData.setAck(true);
        return returnData;
    }

    /**
     * 修改个人敏感信息，data的dataByUser的realName，idCard为新数据。
     * @param data {'realName','idCard'}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo修改成功，新的用户认证码
     * @throws ParseException
     */
    public BaseBean changeSensitiveMessage(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        UserEntity user = userServiceImpl.getOne(data.getReturnCode().split("|")[0]);
        user.setRealName((String) jsonObject.get("realName"));
        user.setIdCard((String) jsonObject.get("idCard"));
        user = changeUserNature(user);
        userServiceImpl.update(user);
        returnData.setReturnDesc("修改个人敏感信息成功！");
        returnData.setAck(true);
        return returnData;
    }

    /**
     * 刷新个人主页信息
     * @param data
     * @return  如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo刷新成功，新的用户认证码,dataByUser和dataByCars为新信息
     * @throws ParseException
     */
    public BaseBean getUserAndCarsInfo(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        String userId = data.getReturnCode().split("|")[0];
        returnData.setReturnDesc("刷新成功！");
        JSONArray cars = carsToJsonArray(this.getCardByUserId(userId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user",JSONObject.parseObject(JSON.toJSONString(this.getUserByUserId(userId))));
        jsonObject.put("cars",cars);
        returnData.setReturnContent(jsonObject);
        return returnData;
    }

    private JSONArray carsToJsonArray(List<CarEntity> list){
        JSONArray jsonArray = new JSONArray();
        list.forEach(carEntity -> {
            String jsonString = JSON.toJSONString(carEntity);
            JSONObject json = JSONObject.parseObject(jsonString);
            jsonArray.add(json);
        });
        return jsonArray;
    }

    /**
     * 通过车辆id获得车辆信息
     * @param data 含有认证码，{"id"}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo获得信息成功，新的用户认证码,dataByCar的第一个为汽车信息
     */
    public BaseBean getCarById(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        CarEntity carEntity = carServiceImpl.getOne((String) jsonObject.get("id"));
        returnData.setReturnDesc("获得车辆信息成功！");
        returnData.setReturnContent(carEntity);
        return returnData;
    }

    /**
     * 通过车辆id删除车辆 {'id'}
     * @param data 含有认证码，dataByCars的第一个的id为汽车id
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo删除成功
     */
    public BaseBean deleteCarById(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        carServiceImpl.delete((String) jsonObject.get("id"));
        returnData.setReturnDesc("删除车辆成功");
        return returnData;
    }

    /**
     * 保存新的车辆信息 {'carCard','carName','carInfo'}
     * @param data data 含有认证码，dataByCars的第一个为车辆信息
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo保存成功
     */
    public BaseBean saveNewCarByUserPhone(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        String userId = data.getReturnCode().split("|")[0];
        CarEntity carEntity = (CarEntity) data.getReturnContent();
        carEntity.setUserId(userId);
        carEntity.setId(PublicMethods.getId());
        carServiceImpl.insert(carEntity);
        returnData.setReturnDesc("保存车辆成功！");
        return returnData;
    }

    /**
     * 更新车辆信息
     * @param data data 含有认证码，{'id','carCard','carName','carInfo'}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo更新车辆信息完成
     */
    public BaseBean updateCar(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        CarEntity newCar = (CarEntity) data.getReturnContent();
        CarEntity oldCar = carServiceImpl.getOne(newCar.getId());
        newCar.setUserId(oldCar.getUserId());
        newCar.setNature(oldCar.getNature());
        this.carServiceImpl.update(newCar);
        returnData.setReturnDesc("更新车辆信息完成！");
        return returnData;
    }

    /**
     * 成为vip
     * @param data 含有验证码，dataByUser的id为用户id
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo
     * @throws ParseException
     */
    public BaseBean toBeVip(BaseBean data) throws ParseException {
        // TODO Auto-generated method stub
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        String userId = data.getReturnCode().split("|")[0];
        UserEntity user = userServiceImpl.getOne(userId);
        VipEntity vip = vipActiveServiceImpl.getVipByUserId(user.getId());
        if (vip == null) {
            Calendar cal = Calendar.getInstance();
            Date date = new Date();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            vip = new VipEntity(PublicMethods.getId(), user.getId(), cal.getTime());
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
        final String sizeOne = "1";
        user = userServiceImpl.getOne(userId);
        System.out.println(user.getNature());
        if (user.getNature().equals(sizeOne)) {
            System.out.println(true);
            user.setNature("2");
        }else {
            System.out.println(false);
        }
        userServiceImpl.update(user);
        returnData.setReturnDesc("成功成为vip！");
        return returnData;
    }

    /**
     * 发送邮件
     * @param data 含有验证码，{"email":email}
     * @return 如果验证失败则返回showInfo验证失败或者邮箱重复，用户验证码为空,成功则返回operation true,showInfo
     * @throws ParseException
     */
    public BaseBean sendEmailForActive(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String userId = data.getReturnCode().split("|")[0];

        String email = (String) jsonObject.get("email");
        if (userServiceImpl.getUserByEmail(email).size() != 0) {
            returnData.setReturnDesc("邮箱重复！");
            returnData.setAck(false);
            return returnData;
        }
        UserEntity user = userServiceImpl.getOne(userId);
        userServiceImpl.update(user);
        EmailActiveEntity emailActiveEntity = emailActiveServiceImpl.getEmailActiveByUserId(user.getId());
        String info = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/znck/activeEmail?code="
                + emailActiveEntity.getId() + "&email=" + email + "'>点击激活</href></h3></body></html>";
        mailServiceImpl.sendHtmlMailByThread("1037426886@qq.com", email, "智能车库邮件激活", info);
        returnData.setReturnDesc("邮箱发送成功！");
        return returnData;
    }


    /**
     * 发送手机验证码（通过用户id获得用户手机号）
     * @param data 含有验证码，通过验证码获得用户id
     * @return 如果验证失败则返回showInfo验证失败或者发送失败，用户验证码为空,成功则返回operation true,showInfo
     */
    public BaseBean sendVerificationCode(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        String userId = data.getReturnCode().split("|")[0];
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        UserEntity user = userServiceImpl.getOne(userId);
        PhoneActiveEntity phoneActive = new PhoneActiveEntity(PublicMethods.getId(), user.getId(), code + "");
        phoneActiveServiceImpl.insert(phoneActive);
        // 需要发送验证码接口
        returnData.setReturnDesc("发送成功！");
        return returnData;
    }

    /**
     * 激活手机 {"code":code}
     * @param data 含有验证码，手机验证码存在dataByUser的id
     * @return 如果验证失败则返回showInfo验证失败或者激活失败，用户验证码为空,成功则返回operation true,showInfo
     */
    public BaseBean activeVerificationCode(BaseBean data) throws ParseException {
        // TODO Auto-generated method stub
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String userId = data.getReturnCode().split("|")[0];
        UserEntity user = userServiceImpl.getOne(userId);
        String code = (String)jsonObject.get("code");
        List<PhoneActiveEntity> codes = phoneActiveServiceImpl.getPhoneActiveByUserPhone(user.getPhone());
        for (PhoneActiveEntity cod : codes) {
            if (cod.getCode().equals(code)) {
                phoneActiveServiceImpl.deleteByUserId(user.getId());
                user.setPhoneNature("1");
                user = changeUserNature(user);
                userServiceImpl.update(user);
                returnData.setReturnDesc("激活成功！");
                return returnData;
            }
        }
        returnData.setReturnDesc("激活失败");
        returnData.setAck(false);
        return returnData;
    }

    /**
     * 增加用户敏感信息
     * @param data 含有验证码，身份证和真实姓名{'realName':realName,'idCard':idCard}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo
     * @throws ParseException
     */
    public BaseBean addUserSensitiveInfo(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String userId = data.getReturnCode().split("|")[0];
        UserEntity oldUser = userServiceImpl.getOne(userId);
        oldUser.setRealName((String)jsonObject.get("realName"));
        oldUser.setIdCard((String)jsonObject.get("idCard"));
        oldUser = changeUserNature(oldUser);
        userServiceImpl.update(oldUser);
        returnData.setReturnDesc("添加成功！");
        return returnData;
    }

    /**
     * 增加用户一般信息
     * @param data 含有验证码，{'nickName':nickName}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo
     * @throws ParseException
     */
    public BaseBean changeGeneralInfo(BaseBean data) throws ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        UserEntity oldUser = userServiceImpl.getOne(data.getReturnCode().split("|")[0]);
        oldUser.setNickName((String) jsonObject.get("nickName"));
        userServiceImpl.update(oldUser);
        returnData.setReturnDesc("添加成功！");
        return returnData;
    }

    /**
     * 修改密码
     * @param data 含有验证码，{'password':password}
     * @return 如果验证失败则返回showInfo验证失败，用户验证码为空,成功则返回operation true,showInfo
     * @throws ParseException
     */
    public BaseBean changePassword(BaseBean data) throws ParseException {
        // TODO Auto-generated method stub
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        UserEntity user = userServiceImpl.getOne(data.getReturnCode().split("|")[0]);
        user.setPassword((String) jsonObject.get("password"));
        userServiceImpl.update(user);
        returnData.setReturnDesc("修改成功！");
        return returnData;
    }

    private void onclock(String threadId) throws InterruptedException {
        do {
            if (StringUtils.isNullOrEmpty(InitDataListener.lockForParking)) {
                InitDataListener.lockForParking = threadId;
            }
            if (StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)) {
                InitDataListener.lockForSpace = threadId;
            }
            Thread.sleep(100);
        } while (!((threadId.equals(InitDataListener.lockForParking) & threadId.equals(InitDataListener.lockForSpace))));
    }

    private void offclock() {
        InitDataListener.lockForParking = null;
        InitDataListener.lockForSpace = null;
    }

    /**
     * 用户预约停车-预约
     * @param data {'id':id,'vipAppParingTimeByString':vipAppParingTimeByString}
     * @return
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean vipAppSaveCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String vipAppParingTimeByString = (String) jsonObject.get("vipAppParingTimeByString");
        // 上锁
        String threadId = PublicMethods.getId();
        onclock(threadId);
        // 从静态变量中获得数据

        SpaceEntity spaceForSave = allParkingModle.getBufferOrFeture(GARAGE_VACANCY);
        if(spaceForSave == null){
            offclock();
            returnData.setReturnDesc("没有车位，请等待车位");
            returnData.setAck(false);
            return returnData;
        }

        SpaceEntity spaceForApp = allParkingModle.getBufferOrFeture(BUFFER_VACANCY);
        if(spaceForApp == null){
            offclock();
            returnData.setReturnDesc("缓冲区已满，请选择普通停车方法");
            returnData.setAck(false);
            return returnData;
        }

        // 更新空间列表，锁定空间
        spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
        spaceForApp.setCarId(carId);
        spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
        allParkingModle.replaceSpace(spaceForSave);
        allParkingModle.replaceSpace(spaceForApp);

        // 新建停车信息
        ParkingEntity parkingEntity = new ParkingEntity(PublicMethods.getId(),carId,spaceForApp.getId()
                ,null,spaceForSave.getId(),allParkingModle.getAppTime(vipAppParingTimeByString)
                ,PublicMethods.getDate(),contrastServiceImpl.getContrastByRealName(VIP_WAIT_USER_PARKING).getId(),"");
        allParkingModle.addPakringEntity(parkingEntity);
        offclock();
        returnData.setReturnDesc("恭喜！停车成功！");
        return returnData;
    }

    /**
     * 预约停车-用户已经来了并且扫码
     *
     * @param data carId : data.dataByCars.0.id
     * @return
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean vipSaveCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");

        // 上锁
        String threadId = PublicMethods.getId();
        onclock(threadId);
        // 从静态变量中获得数据
        ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
        SpaceEntity spaceForApp = allParkingModle.getSpaceById(parking.getInSpaceId());
        // 获得入口id
        SpaceEntity nowSpace = allParkingModle.getBufferOrFeture(ENTRANCE);
        // 更新数据
        spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
        spaceForApp.setCarId(null);
        parking.setInPlaceTime(PublicMethods.getDate());
        parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_IN_THE_PARKING).getId());
        parking.setNowSapceId(nowSpace.getId());
        parking.setWay(nowSpace.getX() + "," + nowSpace.getY() + "," + nowSpace.getZ());
        allParkingModle.replaceSpace(spaceForApp);
        allParkingModle.replaceParking(parking);
        offclock();
        returnData.setReturnDesc("恭喜！停车成功！");
        return returnData;
    }

    /**
     * 普通用户停车
     *
     * @param data
     * @return
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean saveCar(BaseBean data) throws ParseException, InterruptedException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        // 上锁
        String threadId = PublicMethods.getId();
        onclock(threadId);
        // 获得车位，若没有车位则返回没有车位
        SpaceEntity saveSpace = allParkingModle.getBufferOrFeture(GARAGE_VACANCY);
        if(saveSpace == null){
            offclock();
            returnData.setReturnDesc("没有车位，请等待车位");
            returnData.setAck(false);
            return returnData;
        }
        // 获得入口id
        SpaceEntity inSpace = allParkingModle.getBufferOrFeture(ENTRANCE);

        // 更新数据
        saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
        ParkingEntity parking = new ParkingEntity(PublicMethods.getId(),carId,inSpace.getId()
                ,inSpace.getId(),saveSpace.getId(),PublicMethods.getDate()
                ,contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId()
                ,inSpace.getX() + "," + inSpace.getY() + "," + inSpace.getZ());
        allParkingModle.addPakringEntity(parking);
        allParkingModle.replaceSpace(saveSpace);
        offclock();
        returnData.setReturnDesc("恭喜！停车成功！");
        return returnData;
    }

    /**
     * vip用户取车方法，需要两个参数：汽车id和时间
     *
     * @param data {'id':carid,'getOutTimeString':date}
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean vipTakeOutCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String getOutTimeString = (String) jsonObject.get("getOutTimeString");

        String clockId = PublicMethods.getId();
        onclock(clockId);
        // 获得缓冲区，没有缓冲区则返回请选择普通停车方法
        SpaceEntity appGetSpace = allParkingModle.getBufferOrFeture(BUFFER_VACANCY);
        if(appGetSpace == null){
            offclock();
            returnData.setReturnDesc("缓冲区已满，请选择普通取车方法");
            returnData.setAck(false);
            return returnData;
        }
        // 获得基本数据
        ParkingEntity parkingEntity = allParkingModle.getPakringByCarId(carId);
        // 修改数据
        parkingEntity.setOutSpaceId(appGetSpace.getId());
        parkingEntity.setVipGetTime(PublicMethods.getDate());
        parkingEntity.setVipAppGetTime(allParkingModle.getAppTime(getOutTimeString));
        parkingEntity.setNature(contrastServiceImpl.getContrastByRealName(VIP_APP_GETCAR).getId());
        appGetSpace.setCarId(parkingEntity.getCarId());
        appGetSpace.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
        // 更新数据
        allParkingModle.replaceSpace(appGetSpace);
        allParkingModle.replaceParking(parkingEntity);
        offclock();
        returnData.setReturnDesc("恭喜！取车成功！");
        return returnData;
    }

    public BaseBean vipCancelTakeOutCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        // 上锁
        String threadId = PublicMethods.getId();
        onclock(threadId);
        // 从静态变量中获得数据
        ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
        SpaceEntity space = allParkingModle.getSpaceById(parking.getOutSpaceId());

        parking.setOutSpaceId(null);
        parking.setVipGetTime(null);
        parking.setVipAppGetTime(null);
        parking.setNature(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId());
        space.setCarId(null);
        space.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());

        // 更新数据
        allParkingModle.replaceParking(parking);
        allParkingModle.replaceSpace(space);
        offclock();
        returnData.setReturnDesc("恭喜！取消成功！");
        return returnData;
    }

    /**
     * 	普通用户取车方法
     *
     * @param data
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean takeOutCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String clockId = PublicMethods.getId();
        onclock(clockId);
        // 获得数据
        ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
        // 修改数据
        parking.setOutSpaceId(allParkingModle.getBufferOrFeture(EXPORT).getId());
        parking.setOutTime(PublicMethods.getDate());
        parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
        // 更新数据
        allParkingModle.replaceParking(parking);
        offclock();
        returnData.setReturnDesc("恭喜！取车成功！");
        return returnData;
    }

    /**
     * 用户开走车辆的方法
     * @param data
     * @throws InterruptedException
     * @throws ParseException
     */
    public BaseBean getCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String clockId = PublicMethods.getId();
        onclock(clockId);
        ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
        InitDataListener.parkings.remove(parking);
        parking.setGetTime(PublicMethods.getDate());
        ParkingSaveEntity parkingSave = new ParkingSaveEntity(parking);
        parkingSaveServiceImpl.insert(parkingSave);
        offclock();
        returnData.setReturnDesc("恭喜！取车成功！");
        return returnData;
    }

    /**
     * vip用户取消存车的方法
     * @param data returnContext:{id:carId}
     */
    public BaseBean vipCancelSaveCar(BaseBean data) throws InterruptedException, ParseException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String clockId = PublicMethods.getId();
        onclock(clockId);
        ParkingEntity removeParking = allParkingModle.getPakringByCarId(carId);
        SpaceEntity spaceForApp = allParkingModle.getSpaceById(removeParking.getInSpaceId());
        SpaceEntity spaceForSave = allParkingModle.getSpaceById(removeParking.getSaveSpaceId());
        spaceForApp.setCarId(null);
        spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
        spaceForSave.setCarId(null);
        spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
        allParkingModle.replaceSpace(spaceForApp);
        allParkingModle.replaceSpace(spaceForSave);
        InitDataListener.parkings.remove(removeParking);
        offclock();
        returnData.setReturnDesc("恭喜！取消成功！");
        return returnData;
    }

    /**
     * vip用户立即取车方法
     * @param data returnContext:{id:carId}
     */
    public BaseBean vipTakeOutCarNow(BaseBean data) throws ParseException, InterruptedException {
        BaseBean returnData = yZUser(data.getReturnCode());
        if(returnData.getReturnCode() == null){
            return yZUser(data.getReturnCode());
        }
        JSONObject jsonObject= (JSONObject) data.getReturnContent();
        String carId = (String) jsonObject.get("id");
        String clockId = PublicMethods.getId();
        onclock(clockId);
        ParkingEntity parkingOld = allParkingModle.getPakringByCarId(carId);
        ParkingEntity parking = parkingOld;
        // 修改数据
        parking.setOutTime(PublicMethods.getDate());
        parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_GETCAR).getId());
        // 更新数据
        allParkingModle.replaceParking(parking);
        offclock();
        returnData.setReturnDesc("恭喜！取车成功！");
        return returnData;

    }

    private List<CarEntity> getCardByUserId(String userId) {
        List<CarEntity> allCarByUserIdWithOutNature = carServiceImpl.getCarsHaveNatureByUserId(userId);
        List<CarEntity> allCarByUserId = new ArrayList<CarEntity>();
        allCarByUserIdWithOutNature.forEach(car -> {
            CarEntity carWithNature = car;
            List<ParkingEntity> parkings = InitDataListener.parkings.stream()
                    .filter(pa -> pa.getCarId().equals(car.getId())).collect(Collectors.toList());
            if (parkings.size() == 0) {
                carWithNature.setNature(null);
            } else {
                carWithNature.setNature(contrastServiceImpl
                        .getOne(InitDataListener.parkings.stream().filter(pa -> pa.getCarId().equals(car.getId()))
                                .collect(Collectors.toList()).get(0).getNature())
                        .getRealName());
            }
            carWithNature.setUserId(null);
            allCarByUserId.add(carWithNature);
        });
        return allCarByUserId;
    }

    private UserEntity getUserByUserId(String userId){
        return userServiceImpl.getOne(userId);
    }

    private UserEntity changeUserNature(UserEntity user) {
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

    private BaseBean yZUser(String userRz) throws ParseException {
        BaseBean baseBean = new BaseBean();
        baseBean.setReturnCode(null);
        baseBean.setAck(false);
        baseBean.setReturnCode("验证用户码失败！");
        if(StringUtils.isNullOrEmpty(userRz)){
            return baseBean;
        }
        String[] info = userRz.split("|");
        if(info.length<3){
            return baseBean;
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        Date userDate = sdf.parse(info[1]);
        if(userDate.compareTo(PublicMethods.getDate()) == -1){
            baseBean.setReturnCode(null);
            baseBean.setAck(true);
            baseBean.setReturnCode(getReturnCode(info[1]));
        }else{
            return baseBean;
        }
        return baseBean;
    }

    private String getReturnCode(String userId) throws ParseException {
        Date now = PublicMethods.getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, 2);
        Date nextTwoDate = c.getTime();
        c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH,30);
        Date nextMonthDate = c.getTime();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        return userId+"|"+ sdf.format(nextTwoDate) + "|" + sdf.format(nextMonthDate);
    }
}
