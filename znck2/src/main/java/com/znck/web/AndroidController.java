package com.znck.web;

import com.znck.entity.*;
import com.znck.service.AllAndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class AndroidController {
    @Autowired
    private AllAndroidService allAndroidService;

    @RequestMapping("/Android/landing")
    @ResponseBody
    public AndroidData landing(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.landing(data);
    }

    @RequestMapping("/Android/changePassword")
    @ResponseBody
    public AndroidData changePassword(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.changePassword(data);
    }

    @RequestMapping("/Android/changePhone")
    @ResponseBody
    public AndroidData changePhone(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.changePhone(data);
    }

    @RequestMapping("/Android/changeEmail")
    @ResponseBody
    public AndroidData changeEmail(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.changeEmail(data);
    }

    @RequestMapping("/Android/changeSensitiveMessage")
    @ResponseBody
    public AndroidData changeSensitiveMessage(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.changeSensitiveMessage(data);
    }

    @RequestMapping("/Android/changeGeneralInfo")
    @ResponseBody
    public AndroidData changeGeneralInfo(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.changeGeneralInfo(data);
    }

    @RequestMapping("/Android/addUserSensitiveInfo")
    @ResponseBody
    public AndroidData addUserSensitiveInfo(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.addUserSensitiveInfo(data);
    }

    @RequestMapping("/Android/sendVerificationCode")
    @ResponseBody
    public AndroidData sendVerificationCode(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.sendVerificationCode(data);
    }

    @RequestMapping("/Android/activeVerificationCode")
    @ResponseBody
    public AndroidData activeVerificationCode(@RequestBody AndroidData data) throws ParseException {
        return allAndroidService.activeVerificationCode(data);
    }

    @RequestMapping("/Android/registPhone")
    @ResponseBody
    public AndroidData registPhone(@RequestBody AndroidData data) {
        return allAndroidService.regist(data);
    }


    @RequestMapping("/Android/toBeVip")
    @ResponseBody
    public AndroidData toBeVip(@RequestBody AndroidData data) throws ParseException  {
        return allAndroidService.toBeVip(data);
    }

    @RequestMapping("/Android/getCarById")
    @ResponseBody
    public AndroidData getCarById(@RequestBody AndroidData data) throws ParseException  {
        return allAndroidService.getCarById(data);
    }

    @RequestMapping("/Android/deleteCarById")
    @ResponseBody
    public AndroidData deleteCarById(@RequestBody AndroidData data) throws ParseException  {
        return allAndroidService.deleteCarById(data);
    }

    @RequestMapping("/Android/saveNewCar")
    @ResponseBody
    public AndroidData saveNewCar(@RequestBody AndroidData data) throws ParseException  {
        return this.allAndroidService.saveNewCarByUserPhone(data);
    }

    @RequestMapping("/Android/updateCar")
    @ResponseBody
    public AndroidData updateCar(@RequestBody AndroidData data) throws ParseException  {
        return this.allAndroidService.updateCar(data);
    }

    @RequestMapping("/Android/sendEmailForActive")
    @ResponseBody
    public AndroidData sendEmailForActive(@RequestBody AndroidData data) throws ParseException  {
        return allAndroidService.sendEmailForActive(data);
    }

    @RequestMapping("/Android/vipAppSaveCar")
    @ResponseBody
    public AndroidData vipAppSaveCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.vipAppSaveCar(data);
    }

    @RequestMapping("/Android/saveCar")
    @ResponseBody
    public AndroidData saveCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.saveCar(data);
    }

    @RequestMapping("/Android/vipSaveCar")
    @ResponseBody
    public AndroidData vipSaveCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.vipSaveCar(data);
    }

    @RequestMapping("/Android/vipCancelSaveCar")
    @ResponseBody
    public AndroidData vipCancelSaveCar(@RequestBody AndroidData data) throws InterruptedException, ParseException {
        return allAndroidService.vipCancelSaveCar(data);
    }

    @RequestMapping("/Android/vipCancelTakeOutCar")
    @ResponseBody
    public AndroidData vipCancelTakeOutCar(@RequestBody AndroidData data) throws InterruptedException, ParseException {
        return allAndroidService.vipCancelTakeOutCar(data);
    }

    @RequestMapping("/Android/vipTakeOutCar")
    @ResponseBody
    public AndroidData vipTakeOutCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.vipTakeOutCar(data);
    }

    @RequestMapping("/Android/vipTakeOutCarNow")
    @ResponseBody
    public AndroidData vipTakeOutCarNow(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.vipTakeOutCarNow(data);
    }

    @RequestMapping("/Android/takeOutCar")
    @ResponseBody
    public AndroidData takeOutCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.takeOutCar(data);
    }

    @RequestMapping("/Android/getCar")
    @ResponseBody
    public AndroidData getCar(@RequestBody AndroidData data) throws ParseException, InterruptedException {
        return allAndroidService.getCar(data);
    }
}
