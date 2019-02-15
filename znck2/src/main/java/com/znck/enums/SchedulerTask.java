package com.znck.enums;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }
}
