package com.znck.web;

import java.text.ParseException;

import com.znck.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.znck.entity.BaseBean;
import com.znck.entity.CarEntity;
import com.znck.service.AllAdminService;

/**
 * 展示信息的服务接口
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 */
@Controller
public class AdminController {

	@Autowired
	private AllAdminService allAdminService;

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/admin/getPage1")
	@ResponseBody
	public BaseBean getExitCode() throws ParseException {
		return allAdminService.getPage1Info();
	}
	
	@RequestMapping("/admin/test")
	public String userIndex() {
		return "admin/test";
	}

	@RequestMapping("/admin/landing")
	public String returnAdminLanding(){
		return "admin/landing.html";
	}

	@RequestMapping("/admin/page1")
	public String returnPage1(){
		return "admin/index.html";
	}
}
