package com.znck.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.znck.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znck.service.AllparkingService;
import com.znck.service.AllService;

/**
 * 
 * HelloController
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Controller
public class HelloController {

	@Autowired
	private AllService allService;
	
	@Autowired
	private AllparkingService allparkingService;

	@RequestMapping("/getExitCode")
	@ResponseBody
	public String getExitCode() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh") ; //使用了默认的格式创建了一个日期格式化对象。
		String time = dateFormat.format(PublicMethods.getDate()); //可以把日期转换转指定格式的字符串
		time = time + "|" + "exit";
		String code = AesUtil.encrypt(time,"1234567812345678");
		return code;
	}

	@RequestMapping("/adminLanding")
	@ResponseBody
	public ContrastEntity adminLanding(@RequestBody UserEntity data, HttpServletRequest request) {
		ContrastEntity contrast = allService.adminLanding(data);
		String trueString = "true";
		if (trueString.equals(contrast.getId())) {
			request.getSession().setAttribute("admin", "true");
		}
		return allService.adminLanding(data);
	}

	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String password = data.getPassword();
		return allService.changePassword(phone,password);
	}

	@RequestMapping("/changePhone")
	@ResponseBody
	public String changePhone(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String newPhone = data.getPhone();
		request.getSession().removeAttribute("user");
		return allService.changePhone(phone,newPhone);
	}

	@RequestMapping("/changeEmail")
	@ResponseBody
	public String changeEmail(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String email = data.getEmail();
		return allService.changeEmail(phone,email);
	}

	@RequestMapping("/changeSensitiveMessage")
	@ResponseBody
	public String changeSensitiveMessage(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String realName = data.getRealName();
		String idCard = data.getId();
		return allService.changeSensitiveMessage(phone,realName,idCard);
	}

	@RequestMapping("/changeGeneralInfo")
	@ResponseBody
	public String changeGeneralInfo(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String nickName = data.getNickName();
		return allService.changeGeneralInfo(phone,nickName);
	}

	@RequestMapping("/addUserSensitiveInfo")
	@ResponseBody
	public String addUserSensitiveInfo(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		String realName = data.getRealName();
		String idCard = data.getIdCard();
		allService.addUserSensitiveInfo(phone,realName,idCard);
		return "true";
	}

	@RequestMapping("/sendVerificationCode")
	@ResponseBody
	public String sendVerificationCode(HttpServletRequest request){
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		allService.sendVerificationCode(phone);
		return "true";
	}

	@RequestMapping("/activeVerificationCode")
	@ResponseBody
	public String activeVerificationCode(@RequestBody UserEntity data,HttpServletRequest request){
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		allService.activeVerificationCode(data.getId(),phone);
		return "true";
	}

	@RequestMapping("/registPhone")
	@ResponseBody
	public String registPhone(@RequestBody UserEntity data) {
		return allService.regist(data.getPhone());
	}

	@RequestMapping("/getUserByPhone")
	@ResponseBody
	public UserEntity getUserByPhone(HttpServletRequest request) {
		UserEntity userEntity = allService.getUserByPhone(((UserEntity)request.getSession().getAttribute("user")).getPhone());
		userEntity.setId(null);
		return userEntity;
	}

	@RequestMapping("/toBeVip")
	@ResponseBody
	public String toBeVip(HttpServletRequest request) {
		String userId = ((UserEntity)request.getSession().getAttribute("user")).getId();
		return allService.toBeVip(userId);
	}

	@RequestMapping("/getUserCars")
	@ResponseBody
	public List<CarEntity> getCarByUserId(HttpServletRequest request) {
		String userId = ((UserEntity)request.getSession().getAttribute("user")).getId();
		return this.allService.getCardByUserId(userId);
	}

	@RequestMapping("/getCarById")
	@ResponseBody
	public CarEntity getCarById(@RequestBody CarEntity data) {
		String carId = data.getId();
		return allService.getCarById(carId);
	}

	@RequestMapping("/deleteCarById")
	@ResponseBody
	public boolean deleteCarById(@RequestBody CarEntity data) {
		allService.deleteCarById(data.getId());
		return true;
	}

	@RequestMapping("/landing")
	@ResponseBody
	public String landing(@RequestBody UserEntity data, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserEntity user = allService.landing(data);
		if (user != null) {
			session.setAttribute("user", user);
			return "true";
		}else {
			return null;
		}
	}


	@RequestMapping("/saveNewCar")
	@ResponseBody
	public String saveNewCar(@RequestBody CarEntity data,HttpServletRequest request) {
		String userId = ((UserEntity)request.getSession().getAttribute("user")).getId();
		this.allService.saveNewCarByUserPhone(data, userId);
		return "true";
	}
	
	@RequestMapping("/updateCar")
	@ResponseBody
	public String updateCar(@RequestBody CarEntity data,HttpServletRequest request) {
		this.allService.updateCar(data);
		return "true";
	}


	@RequestMapping("/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/jumpToUrl")
	public String jumpToHello(Model model, HttpServletRequest request) {
		Map<String, String> nameValue = parseFrom(request);
		for (Entry<String, String> m : nameValue.entrySet()) {
			model.addAttribute(m.getKey(), m.getValue());
		}
		
		return request.getParameter("url");
	}

	@RequestMapping("/sendEmailForActive")
	@ResponseBody
	public String sendEmailForActive(@RequestBody UserEntity data,HttpServletRequest request) {
		String phone = ((UserEntity)request.getSession().getAttribute("user")).getPhone();
		return allService.sendEmailForActive(data.getEmail(),phone);
	}

	@RequestMapping("/activeEmail")
	public String activeEmail(HttpServletRequest request) {
		allService.activeEmail(request.getParameter("code"), request.getParameter("email"));
		return "index";
	}

	@RequestMapping("/vipAppSaveCar")
	@ResponseBody
	public String vipAppSaveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		String appTime = data.getRealName();
		return allparkingService.vipAppSaveCar(carId,appTime);
	}
	
	@RequestMapping("/saveCar")
	@ResponseBody
	public String saveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		return allparkingService.saveCar(carId);
	}

	@RequestMapping("/vipSaveCar")
	@ResponseBody
	public void vipSaveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService.vipSaveCar(carId);
	}

	@RequestMapping("/vipCancelSaveCar")
	@ResponseBody
	public void vipCancelSaveCar(@RequestBody ContrastEntity data) throws InterruptedException {
		String carId = data.getId();
		allparkingService.vipCancelSaveCar(carId);
	}

	@RequestMapping("/vipCancelTakeOutCar")
	@ResponseBody
	public void vipCancelTakeOutCar(@RequestBody ContrastEntity data) throws InterruptedException {
		String carId = data.getId();
		allparkingService.vipCancelTakeOutCar(carId);
	}

	@RequestMapping("/vipTakeOutCar")
	@ResponseBody
	public void vipTakeOutCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		String appTime = data.getRealName();
		allparkingService.vipTakeOutCar(carId,appTime);
	}

	@RequestMapping("/vipTakeOutCarNow")
	@ResponseBody
	public void vipTakeOutCarNow(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService.vipTakeOutCarNow(carId);
	}

	@RequestMapping("/takeOutCar")
	@ResponseBody
	public String takeOutCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService.takeOutCar(carId);
		return "true";
	}

	@RequestMapping("/getCar")
	@ResponseBody
	public String getCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService.getCar(carId);
		return "true";
	}

	private static Map<String, String> parseFrom(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<>(200);
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			parameters.put(parameterName, request.getParameter(parameterName));
		}
		return parameters;
	}

}
