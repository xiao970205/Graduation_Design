package com.znck.enums;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.znck.service.AllService;

/**
 * 
 * SchedulerTask
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Component
public class SchedulerTask {

	private AllService allService;

	@Scheduled(cron = "0 0 1 * * ?")
	private void process() {
		allService.endVip();
	}
}
