package com.hibernate.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.hibernate.entity.Car;
import com.hibernate.entity.CarRun;
import com.hibernate.entity.Run;

public class Test {
    public static void main(String[] args) throws InterruptedException, ParseException {

    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static Date set1() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        return date2;
    }

    public void save() throws ParseException {
        Run run = new Run();
        run.getCar();
        // run.saveCar("642880079c2b4c7eb9b2a58780e2a957", "6f0f4516ec04414f90c3cc72e25f6d47", null);
        // Thread.sleep(1000);
        // run.saveCar("642880079c2b4c7eb9b2a58780e2a957", "7050f26ee0fa4e7b8571ad3f43a83884", null);
        // Thread.sleep(1000);
        // run.saveCar("642880079c2b4c7eb9b2a58780e2a957", "721799c190734042a0329c6ddacc1040", null);
        // Thread.sleep(1000);
        // run.saveCar("642880079c2b4c7eb9b2a58780e2a957", "9aafff25567b4a5b8f429ba2b6de5494", null);
        // Thread.sleep(1000);
        // run.saveCar("642880079c2b4c7eb9b2a58780e2a957", "a79f4d9a52ae4acda5d361306464f7af", null);
    }
}
