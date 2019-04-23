package com.znck.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.AesUtil;
import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.EmailActiveEntity;
import com.znck.entity.ParkingEntity;
import com.znck.entity.PhoneActiveEntity;
import com.znck.entity.PublicMethods;
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
	private EmailActiveServiceImpl emailActiveServiceImpl;

	@Autowired
	private PhoneActiveServiceImpl phoneActiveServiceImpl;

	@Autowired
	private VipServiceImpl vipActiveServiceImpl;

	@Autowired
	private MailServiceImpl mailServiceImpl;
	
	@Autowired
	private SpaceServiceImpl spaceServiceImpl;

	public ContrastEntity adminLanding(UserEntity data) {
		ContrastEntity contrastEntity = new ContrastEntity();
		String admin = "admin";
		String password = "1234567890asdfgh";
		if (admin.equals(AesUtil.decrypt(data.getId(), password))) {
			contrastEntity.setId("true");
		} else {
			contrastEntity.setId("false");
		}
		return contrastEntity;
	}

	public String regist(String phone) {
		ContrastEntity contrast = new ContrastEntity();
		System.out.println(phone);
		if (userServiceImpl.getUserByPhone(phone) != null) {
			// 已注册
			return "true";
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
			contrast.setId("true");
			EmailActiveEntity emailActiveEntity = new EmailActiveEntity(PublicMethods.getId(), user.getId());
			emailActiveServiceImpl.insert(emailActiveEntity);
		}
		return "false";
	}


	public UserEntity landing(UserEntity massage) {
		return userServiceImpl.findByUserNameAndPassword(massage.getPhone(), massage.getPassword());
	}

	public UserEntity getUserByPhone(String phone) {
		return userServiceImpl.getUserByPhone(phone);
	}

	public String changePhone(String oldPhone,String newPhone) {
		UserEntity user = userServiceImpl.getUserByPhone(oldPhone);
		user.setPhone(newPhone);
		user.setPhoneNature("0");
		user = changeUserNature(user);
		userServiceImpl.update(user);
		return "true";
	}

	public String changeEmail(String phone ,String email) {
		UserEntity user = userServiceImpl.getUserByPhone(phone);
		user.setEmail(email);
		user.setEmailNature("0");
		user = changeUserNature(user);
		userServiceImpl.update(user);
		return "true";
	}

	public String changeSensitiveMessage(String phone,String realName,String idCard) {
		UserEntity user = userServiceImpl.getUserByPhone(phone);
		user.setRealName(realName);
		user.setIdCard(idCard);
		user = changeUserNature(user);
		userServiceImpl.update(user);
		return "true";
	}

	public List<CarEntity> getCardByUserId(String userId) {
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
				if(carWithNature.getNature().equals(contrastServiceImpl.getContrastByRealName("等待用户停车中").getId())) {
					carWithNature.setCarInSpace(parkings.get(0).getInSpaceId());
				}
				if(carWithNature.getNature().equals(contrastServiceImpl.getContrastByRealName("等待用户取车中").getId())) {
					carWithNature.setCarInSpace(parkings.get(0).getOutSpaceId());
				}
			}
			carWithNature.setUserId(null);
			allCarByUserId.add(carWithNature);
		});
		return allCarByUserId;
	}

	public CarEntity getCarById(String carId) {
		CarEntity carEntity = carServiceImpl.getOne(carId);
		carEntity.setUserId(null);
		carEntity.setId(null);
		return carEntity;
	}

	public void deleteCarById(String carId) {
		carServiceImpl.delete(carId);
	}

	public void saveNewCarByUserPhone(CarEntity carEntity,String userId) {
		carEntity.setUserId(userId);
		carEntity.setId(PublicMethods.getId());
		carServiceImpl.insert(carEntity);
	}

	public void updateCar(CarEntity newCar) {
		CarEntity oldCar = carServiceImpl.getOne(newCar.getId());
		newCar.setUserId(oldCar.getUserId());
		newCar.setNature(oldCar.getNature());
		this.carServiceImpl.update(newCar);
	}

	public String toBeVip(String userId) {
		// TODO Auto-generated method stub
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
		return "true";
	}

	public String sendEmailForActive(String email,String phone) {
		if (userServiceImpl.getUserByEmail(email).size() != 0) {
			return "false";
		}
		UserEntity user = userServiceImpl.getUserByPhone(phone);
		userServiceImpl.update(user);
		EmailActiveEntity emailActiveEntity = emailActiveServiceImpl.getEmailActiveByUserId(user.getId());
		String info = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/znck/activeEmail?code="
				+ emailActiveEntity.getId() + "&email=" + email + "'>点击激活</href></h3></body></html>";
		mailServiceImpl.sendHtmlMailByThread("1037426886@qq.com", email, "智能车库邮件激活", info);
		return "true";
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

	public void sendVerificationCode(String phone) {
		int code = (int) ((Math.random() * 9 + 1) * 1000);
		UserEntity user = userServiceImpl.getUserByPhone(phone);
		PhoneActiveEntity phoneActive = new PhoneActiveEntity(PublicMethods.getId(), user.getId(), code + "");
		phoneActiveServiceImpl.insert(phoneActive);
		// 需要发送验证码接口
	}

	public String activeVerificationCode(String code,String phone) {
		// TODO Auto-generated method stub
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

	public void addUserSensitiveInfo(String phone,String realName,String idCard) {
		UserEntity oldUser = userServiceImpl.getUserByPhone(phone);
		oldUser.setRealName(realName);
		oldUser.setIdCard(idCard);
		oldUser = changeUserNature(oldUser);
		userServiceImpl.update(oldUser);
	}

	public String changeGeneralInfo(String phone,String nickName) {
		UserEntity oldUser = userServiceImpl.getUserByPhone(phone);
		oldUser.setNickName(nickName);
		userServiceImpl.update(oldUser);
		return "true";
	}

	public String changePassword(String phone,String password) {
		// TODO Auto-generated method stub
		UserEntity user = userServiceImpl.getUserByPhone(phone);
		user.setPassword(password);
		userServiceImpl.update(user);
		return "true";
	}

	public void endVip() {
		vipActiveServiceImpl.deleteNowBiggerEndDate();
	}
	
	public String getBufferCode(UserEntity userEntity) throws ParseException {
		String bufferId = userEntity.getId();
		SpaceEntity buffer = spaceServiceImpl.getOne(bufferId);
		if(buffer == null) {
			return "false";
		}
		if(buffer.getNature().equals(contrastServiceImpl.getContrastByRealName("缓冲区-占用").getId())) {
			return getCode("|buffer|"+bufferId);
		}
		if(buffer.getNature().equals(contrastServiceImpl.getContrastByRealName("缓冲区-空置").getId())) {
			return getCode("|buffer|"+bufferId);
		}
		return "false";
	}
	
	public String getCode(String input) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh") ;
		String time = dateFormat.format(PublicMethods.getDate());
		time = time + input;
		String code = AesUtil.encrypt(time,"1234567812345678");
		return code;
	}
	
	public String[] getDecryptInfos(String orgainInfo) {
		String info = "";
		try {
			info = AesUtil.encrypt(orgainInfo, "1234567812345678");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return info.split("|");
	}
	
}
