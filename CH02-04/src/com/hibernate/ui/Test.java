package com.hibernate.ui;

import java.util.Date;
import java.util.UUID;

import com.hibernate.entity.Parking;
import com.hibernate.entity.ParkingRun;

public class Test {
    public static void main(String[] args) {
        ParkingRun run = new ParkingRun();
        Parking parking = new Parking();
        Date date = new Date();
        parking.setId(getId());
        parking.setInTime(date);
        run.save(parking);
    }
    
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
