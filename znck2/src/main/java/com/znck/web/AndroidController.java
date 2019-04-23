package com.znck.web;

import com.znck.entity.*;
import com.znck.service.serviceImpl.AllAndroidForChenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class AndroidController {

    @Autowired
    private AllAndroidForChenService allAndroidForChenService;

    @RequestMapping("/Android/landing")
    @ResponseBody
    public BaseBean landing(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.landing(data);
    }

    @RequestMapping("/Android/changePassword")
    @ResponseBody
    public BaseBean changePassword(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.changePassword(data);
    }

    @RequestMapping("/Android/changePhone")
    @ResponseBody
    public BaseBean changePhone(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.changePhone(data);
    }

    @RequestMapping("/Android/changeEmail")
    @ResponseBody
    public BaseBean changeEmail(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.changeEmail(data);
    }

    @RequestMapping("/Android/changeSensitiveMessage")
    @ResponseBody
    public BaseBean changeSensitiveMessage(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.changeSensitiveMessage(data);
    }

    @RequestMapping("/Android/changeGeneralInfo")
    @ResponseBody
    public BaseBean changeGeneralInfo(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.changeGeneralInfo(data);
    }

    @RequestMapping("/Android/addUserSensitiveInfo")
    @ResponseBody
    public BaseBean addUserSensitiveInfo(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.addUserSensitiveInfo(data);
    }

    @RequestMapping("/Android/sendVerificationCode")
    @ResponseBody
    public BaseBean sendVerificationCode(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.sendVerificationCode(data);
    }

    @RequestMapping("/Android/activeVerificationCode")
    @ResponseBody
    public BaseBean activeVerificationCode(@RequestBody BaseBean data) throws ParseException {
        return allAndroidForChenService.activeVerificationCode(data);
    }

    @RequestMapping("/Android/registPhone")
    @ResponseBody
    public BaseBean registPhone(@RequestBody BaseBean data) {
        return allAndroidForChenService.regist(data);
    }


    @RequestMapping("/Android/toBeVip")
    @ResponseBody
    public BaseBean toBeVip(@RequestBody BaseBean data) throws ParseException  {
        return allAndroidForChenService.toBeVip(data);
    }

    @RequestMapping("/Android/getCarById")
    @ResponseBody
    public BaseBean getCarById(@RequestBody BaseBean data) throws ParseException  {
        return allAndroidForChenService.getCarById(data);
    }

    @RequestMapping("/Android/deleteCarById")
    @ResponseBody
    public BaseBean deleteCarById(@RequestBody BaseBean data) throws ParseException  {
        return allAndroidForChenService.deleteCarById(data);
    }

    @RequestMapping("/Android/saveNewCar")
    @ResponseBody
    public BaseBean saveNewCar(@RequestBody BaseBean data) throws ParseException  {
        return this.allAndroidForChenService.saveNewCarByUserPhone(data);
    }

    @RequestMapping("/Android/updateCar")
    @ResponseBody
    public BaseBean updateCar(@RequestBody BaseBean data) throws ParseException  {
        return this.allAndroidForChenService.updateCar(data);
    }

    @RequestMapping("/Android/sendEmailForActive")
    @ResponseBody
    public BaseBean sendEmailForActive(@RequestBody BaseBean data) throws ParseException  {
        return allAndroidForChenService.sendEmailForActive(data);
    }

    @RequestMapping("/Android/vipAppSaveCar")
    @ResponseBody
    public BaseBean vipAppSaveCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.vipAppSaveCar(data);
    }

    @RequestMapping("/Android/saveCar")
    @ResponseBody
    public BaseBean saveCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.saveCar(data);
    }

    @RequestMapping("/Android/vipSaveCar")
    @ResponseBody
    public BaseBean vipSaveCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.vipSaveCar(data);
    }

    @RequestMapping("/Android/vipCancelSaveCar")
    @ResponseBody
    public BaseBean vipCancelSaveCar(@RequestBody BaseBean data) throws InterruptedException, ParseException {
        return allAndroidForChenService.vipCancelSaveCar(data);
    }

    @RequestMapping("/Android/vipCancelTakeOutCar")
    @ResponseBody
    public BaseBean vipCancelTakeOutCar(@RequestBody BaseBean data) throws InterruptedException, ParseException {
        return allAndroidForChenService.vipCancelTakeOutCar(data);
    }

    @RequestMapping("/Android/vipTakeOutCar")
    @ResponseBody
    public BaseBean vipTakeOutCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.vipTakeOutCar(data);
    }

    @RequestMapping("/Android/vipTakeOutCarNow")
    @ResponseBody
    public BaseBean vipTakeOutCarNow(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.vipTakeOutCarNow(data);
    }

    @RequestMapping("/Android/takeOutCar")
    @ResponseBody
    public BaseBean takeOutCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.takeOutCar(data);
    }

    @RequestMapping("/Android/getCar")
    @ResponseBody
    public BaseBean getCar(@RequestBody BaseBean data) throws ParseException, InterruptedException {
        return allAndroidForChenService.getCar(data);
    }
}
