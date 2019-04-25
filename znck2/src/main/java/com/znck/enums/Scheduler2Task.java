package com.znck.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.znck.service.AllParkingServiceForRuning;

/**
 * 
 * Scheduler2Task
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Component
public class Scheduler2Task {
	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 500)
	public void reportCurrentTime() throws ParseException, InterruptedException {
//		allParkingServiceForRuning.parkingRun();
//		allParkingServiceForRuning.showInfo();
	}

	@Autowired
	private AllParkingServiceForRuning allParkingServiceForRuning;
}
