package com.hibernate.ui;

import java.util.UUID;

import com.hibernate.entity.ParkingRun;

public class Test {
    public static void main(String[] args) {
        ParkingRun parkingrun = new ParkingRun();
        System.out.println(parkingrun.get("fba76719405447cdbccd1eab55961ead").getOutTime());
        
        
    }
    
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
