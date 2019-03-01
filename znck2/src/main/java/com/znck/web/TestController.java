package com.znck.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znck.entity.UserEntity;

@Controller
public class TestController {

	@RequestMapping("/test/")
	@ResponseBody
	public void changePassword(@RequestBody UserEntity data) {
	}
}
