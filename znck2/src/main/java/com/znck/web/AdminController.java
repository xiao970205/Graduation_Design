package com.znck.web;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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

	private AllAdminService allAdminService;
	
	@RequestMapping("/admin/getPage1")
	@ResponseBody
	public JSONObject getExitCode() throws ParseException {
		JSONObject o = allAdminService.getPage1();
		return o;
	}
}
