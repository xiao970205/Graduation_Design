package com.znck.web;

import com.znck.entity.*;
import com.znck.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/tojiamitest")
    public String tojiamitest() {
        return "jiamitest";
    }

    @RequestMapping("/tojiamitest22")
    public String tojiamitest22() {
        return "jiami2";
    }

    @RequestMapping("/jiamitest2")
    @ResponseBody
    public String jiamitest2(@RequestBody String data) throws Exception {
        String data2 = "肖舒翔";
        AESUtil a = new AESUtil();
        return null;
    }

    @RequestMapping("/jiamitest3")
    @ResponseBody
    public String jiamitest3(@RequestBody String data) throws Exception {
        String data2 = "肖舒翔";
        AesUtil3 a = new AesUtil3();
        System.out.println(1+data);
        System.out.println(2+AesUtil3.desEncrypt(data));
        System.out.println(3+AesUtil3.encrypt(data2));
        return null;
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

    @RequestMapping("/changeUserInfo")
    @ResponseBody
    public UserEntity changeUserInfo(@RequestBody UserEntity data) {
        return allService.changeUserInfo(data);
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
    public UserEntity landing(@RequestBody UserEntity data) {
        return allService.landing(data);
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
