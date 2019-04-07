package com.znck.web;

import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.UserEntity;
import com.znck.service.AllParkingService2;
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
	private AllParkingService2 allparkingService2;

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
	public String changePassword(@RequestBody UserEntity data) {
		return allService.changePassword(data);
	}

	@RequestMapping("/changePhone")
	@ResponseBody
	public String changePhone(@RequestBody UserEntity data) {
		return allService.changePhone(data);
	}

	@RequestMapping("/changeEmail")
	@ResponseBody
	public String changeEmail(@RequestBody UserEntity data) {
		return allService.changeEmail(data);
	}

	@RequestMapping("/changeSensitiveMessage")
	@ResponseBody
	public String changeSensitiveMessage(@RequestBody UserEntity data) {
		return allService.changeSensitiveMessage(data);
	}

	@RequestMapping("/changeGeneralInfo")
	@ResponseBody
	public String changeGeneralInfo(@RequestBody UserEntity data) {
		return allService.changeGeneralInfo(data);
	}

	@RequestMapping("/addUserSensitiveInfo")
	@ResponseBody
	public String addUserSensitiveInfo(@RequestBody UserEntity data) {
		System.out.println(666);
		allService.addUserSensitiveInfo(data);

		return "true";
	}

	@RequestMapping("/sendVerificationCode")
	@ResponseBody
	public String sendVerificationCode(@RequestBody UserEntity data){
		allService.sendVerificationCode(data);
		return "true";
	}

	@RequestMapping("/activeVerificationCode")
	@ResponseBody
	public String activeVerificationCode(@RequestBody UserEntity data){
		allService.activeVerificationCode(data);
		return "true";
	}

	@RequestMapping("/registPhone")
	@ResponseBody
	public ContrastEntity registPhone(@RequestBody UserEntity data) {
		return allService.regist(data);
	}

	@RequestMapping("/getUserByPhone")
	@ResponseBody
	public UserEntity getUserByPhone(HttpServletRequest request) {
		return allService.getUserByPhone(((UserEntity)request.getSession().getAttribute("user")).getPhone());
	}

	@RequestMapping("/toBeVip")
	@ResponseBody
	public UserEntity toBeVip(@RequestBody UserEntity data) {
		return allService.toBeVip(data);
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
		return allService.getCarById(data);
	}

	@RequestMapping("/deleteCarById")
	@ResponseBody
	public boolean deleteCarById(@RequestBody CarEntity data) {
		allService.deleteCarById(data);
		return true;
	}

	@RequestMapping("/landing")
	@ResponseBody
	public UserEntity landing(@RequestBody UserEntity data, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserEntity user = allService.landing(data);
		if (user != null) {
			session.setAttribute("user", user);
		}
		return user;
	}


	@RequestMapping("/updateCar")
	@ResponseBody
	public String updateCar(@RequestBody CarEntity data,HttpServletRequest request) {
		String userId = ((UserEntity)request.getSession().getAttribute("user")).getId();
		this.allService.updateCar(data, userId);
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
	public ContrastEntity sendEmailForActive(@RequestBody UserEntity data,HttpServletRequest request) {
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
	public void vipAppSaveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		String appTime = data.getRealName();
		allparkingService2.vipAppSaveCar(carId,appTime);
	}

	@RequestMapping("/saveCar")
	@ResponseBody
	public void saveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService2.saveCar(carId);
	}

	@RequestMapping("/vipSaveCar")
	@ResponseBody
	public void vipSaveCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService2.vipSaveCar(carId);
	}

	@RequestMapping("/vipCancelSaveCar")
	@ResponseBody
	public void vipCancelSaveCar(@RequestBody ContrastEntity data) throws InterruptedException {
		String carId = data.getId();
		allparkingService2.vipCancelSaveCar(carId);
	}

	@RequestMapping("/vipTakeOutCar")
	@ResponseBody
	public void vipTakeOutCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		String appTime = data.getRealName();
		allparkingService2.vipTakeOutCar(carId,appTime);
	}

	@RequestMapping("/vipTakeOutCarNow")
	@ResponseBody
	public void vipTakeOutCarNow(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService2.vipTakeOutCarNow(carId);
	}

	@RequestMapping("/takeOutCar")
	@ResponseBody
	public void takeOutCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService2.takeOutCar(carId);
	}

	@RequestMapping("/getCar")
	@ResponseBody
	public void getCar(@RequestBody ContrastEntity data) throws ParseException, InterruptedException {
		String carId = data.getId();
		allparkingService2.getCar(carId);
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
