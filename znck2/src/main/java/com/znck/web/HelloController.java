package com.znck.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.UserEntity;
import com.znck.service.CarService;
import com.znck.service.RunService;
import com.znck.service.UserService;

@Controller
public class HelloController {
    
    @Autowired
    private RunService runService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CarService carService;
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/regphone")
    @ResponseBody
    public ContrastEntity regphone(@RequestBody String data){
        String massage = userService.regist(data);
        ContrastEntity contrast = new ContrastEntity();
        contrast.setId(massage);
        return contrast;
    }
    
    @RequestMapping("/getUserByPhone")
    @ResponseBody
    public UserEntity getUserByPhone(@RequestBody String data){
        UserEntity user = this.userService.getUserByPhone(data);
        return user;
    }
    
    @RequestMapping("/changeUserInfo")
    @ResponseBody
    public UserEntity changeUserInfo(@RequestBody UserEntity data){
        return this.userService.changeUserInfo(data);
    }

    @RequestMapping("/getCarByUserId")
    @ResponseBody
    public List<CarEntity> getCarByUserId(@RequestBody String data){
        return this.carService.getCardByUserId(data);
    }
    
    @RequestMapping("/landing")
    @ResponseBody
    public UserEntity landing(@RequestBody String data){
        return userService.landing(data);
    }
    
    @RequestMapping("/jumpToUrl")
    public String jumpToHello(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name,HttpServletRequest request) {
        model.addAttribute("phone", request.getParameter("phone"));
        return request.getParameter("url");
    }
}
