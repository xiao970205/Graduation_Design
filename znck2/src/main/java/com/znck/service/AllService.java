package com.znck.service;

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

	public ContrastEntity regist(UserEntity data) {
		String phone = data.getPhone();
		ContrastEntity contrast = new ContrastEntity();
		System.out.println(phone);
		if (userServiceImpl.getUserByPhone(phone) != null) {
			// 已注册
			contrast=null;
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
		return contrast;
	}

	public UserEntity landing(UserEntity massage) {
		return userServiceImpl.findByUserNameAndPassword(massage.getPhone(), massage.getPassword());
	}

	public UserEntity getUserByPhone(String phone) {
		return userServiceImpl.getUserByPhone(phone);
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

	public List<CarEntity> getCardByUserId(String phone) {
		String userId = userServiceImpl.getUserByPhone(phone).getId();
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
		carEntity.setId(PublicMethods.getId());
		carServiceImpl.insert(carEntity);
	}

	public void updateCarWithoutUserId(CarEntity car) {
		CarEntity oldCar = carServiceImpl.getOne(car.getId());
		car.setId(oldCar.getId());
		car.setUserId(oldCar.getUserId());
		this.carServiceImpl.update(car);
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
		final int sizeOne = 1;
		if (user.getNature().equals(sizeOne)) {
			user.setNature("2");
		}
		userServiceImpl.update(user);
		return user;
	}

	public ContrastEntity sendEmailForActive(UserEntity data) {
		ContrastEntity contrast = new ContrastEntity();
		String userEmail = data.getEmail();
		if (userServiceImpl.getUserByEmail(userEmail).size() != 0) {
			contrast.setRealName("false");
			return contrast;
		}
		contrast.setRealName("true");
		UserEntity user = userServiceImpl.getUserByPhone(data.getPhone());
		userServiceImpl.update(user);
		EmailActiveEntity emailActiveEntity = emailActiveServiceImpl.getEmailActiveByUserId(user.getId());
		String info = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/znck/activeEmail?code="
				+ emailActiveEntity.getId() + "&email=" + userEmail + "'>点击激活</href></h3></body></html>";
//		mailServiceImpl.sendHtmlMailByThread("1037426886@qq.com", userEmail, "智能车库邮件激活", info);
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
		int code = (int) ((Math.random() * 9 + 1) * 1000);
		user = userServiceImpl.getUserByPhone(phone);
		PhoneActiveEntity phoneActive = new PhoneActiveEntity(PublicMethods.getId(), user.getId(), code + "");
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
}
