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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.UserEntity;
import com.znck.enums.InitDataListener;
import com.znck.service.AllService;

/**
 * 
 * HelloController
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Controller
public class HelloController {

    @Autowired
    private AllService allService;
    
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
    public String sendVerificationCode(@RequestBody UserEntity data) throws ParseException {
        allService.sendVerificationCode(data);
        return "true";
    }
    
    @RequestMapping("/activeVerificationCode")
    @ResponseBody
    public String activeVerificationCode(@RequestBody UserEntity data) throws ParseException {
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
    public UserEntity getUserByPhone(@RequestBody UserEntity data) {
        return allService.getUserByPhone(data);
    }

    @RequestMapping("/toBeVip")
    @ResponseBody
    public UserEntity toBeVip(@RequestBody UserEntity data) {
        return allService.toBeVip(data);
    }

    @RequestMapping("/getCarByUserId")
    @ResponseBody
    public List<CarEntity> getCarByUserId(@RequestBody ContrastEntity data) {
        return this.allService.getCardByUserId(data);
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
    public UserEntity landing(@RequestBody UserEntity data,HttpServletRequest request) {
        System.out.println("textInfo:"+InitDataListener.textInfo);
        InitDataListener.textInfo = "2";
        HttpSession session = request.getSession();
        UserEntity user = allService.landing(data);
        if(user != null){
            session.setAttribute("user", user);
        }
        return user;
    }

    @RequestMapping("/saveNewCar")
    @ResponseBody
    public String saveNewCarByUserPhone(@RequestBody CarEntity data) {
        this.allService.saveNewCarByUserPhone(data);
        return "true";
    }

    @RequestMapping("/updateCarWithoutUserId")
    @ResponseBody
    public String updateCarWithoutUserId(@RequestBody CarEntity data) {
        this.allService.updateCarWithoutUserId(data);
        return "true";
    }

    @RequestMapping("/parkingStopCar")
    @ResponseBody
    public String parkingStopCar(@RequestBody UserEntity data) throws ParseException {
        allService.saveCar(data);
        return "true";
    }

    @RequestMapping("/parkingGetCar")
    @ResponseBody
    public String parkingGetCar(@RequestBody UserEntity data) throws ParseException {
        allService.parkingGetCar(data);
        return "true";
    }

    @RequestMapping("/jumpToUrl")
    public String jumpToHello(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name,
        HttpServletRequest request) {
        Map<String, String> nameValue = parseFrom(request);
        for (Entry<String, String> m : nameValue.entrySet()) {
            model.addAttribute(m.getKey(), m.getValue());
        }
        return request.getParameter("url");
    }

    @RequestMapping("/sendEmailForActive")
    @ResponseBody
    public ContrastEntity sendEmailForActive(@RequestBody UserEntity data) {
        return allService.sendEmailForActive(data);
    }
    
    @RequestMapping("/activeEmail")
    public String activeEmail(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name,
        HttpServletRequest request) {
        allService.activeEmail(request.getParameter("code"), request.getParameter("email"));
        return "index";
    }
    
    public static Map<String, String> parseFrom(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>(200);
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameters.put(parameterName, request.getParameter(parameterName));
        }
        return parameters;
    }

}
