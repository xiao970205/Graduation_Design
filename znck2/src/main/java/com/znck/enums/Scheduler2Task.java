package com.znck.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.znck.service.AllParkingServiceForRuning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.znck.service.AllparkingService;

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

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() throws ParseException, InterruptedException {
//		System.out.println("现在时间：" + DATEFORMAT.format(new Date()));
		allParkingServiceForRuning.parkingRun();
		allParkingServiceForRuning.showInfo();
	}

	@Autowired
	private AllparkingService allParkingService;

	@Autowired
	private AllParkingServiceForRuning allParkingServiceForRuning;
}
