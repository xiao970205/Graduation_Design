package com.znck.web;

import com.znck.entity.ContrastEntity;
import com.znck.entity.UserEntity;
import com.znck.service.RunService;
import com.znck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    
    @Autowired
    private RunService runService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/hello")
    public String hello(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);

        return "hello";
    }
    
    @RequestMapping("/saveCar2")
    public void saveCar(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name){
        System.out.println(model);
    }
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/getusername")
    @ResponseBody
    public String get1(@RequestBody String data){
        System.out.println(data);
        return "666";
    }
    
    @RequestMapping("/jump2")
    public String jump2(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        System.out.println(1);
        model.addAttribute("name", name);
        return "phone";
    }
    
    @RequestMapping("/jumpToIndex")
    public String jumpToIndex(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
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

    @RequestMapping("/getCarByUserId")
    @ResponseBody
    public UserEntity getCarByUserId(@RequestBody String data){
        System.out.println("id"+data);
        return null;
    }
    
    @RequestMapping("/landing")
    @ResponseBody
    public UserEntity landing(@RequestBody String data){
        return userService.landing(data);
    }
    
    @RequestMapping("/jumpToHello")
    public String jumpToHello(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name,HttpServletRequest request) {
        model.addAttribute("phone", request.getParameter("phone"));
        return "hello";
    }
}
