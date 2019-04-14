package com.znck.enums;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池类
 * 
 * @author 肖舒翔
 * 2019-02-28
 * @version 1.0
 */
@Configuration
@EnableAsync
public class TaskExecutePool {

	private int corePoolSize = 10;
	 
    private int maxPoolSize = 50;
 
    private int queueSize = 10;
 
    private int keepAlive = 60;
 
    @Bean
    public Executor testExecutorPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.setThreadNamePrefix("TestExecutorPool-");
 
        executor.initialize();
        return executor;
    }
}
