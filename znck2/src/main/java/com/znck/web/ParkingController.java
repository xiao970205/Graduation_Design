 package com.znck.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znck.entity.ParkingEntity;
import com.znck.service.ParkingService;

@RestController
 public class ParkingController {   
    @Autowired
    private ParkingService parkingService;
    
    @RequestMapping("/save0")
    public void update0() throws ParseException{
        ParkingEntity parking = new ParkingEntity();
        parking.setId(getId());
        parking.setInTime(getNowDate());
        parking.setOutTime(getNowDate());
        parkingService.save(parking);
    }
    
    public Date getNowDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        return date2;
    }
    
    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
