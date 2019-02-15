package com.znck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * ZnckApplication
 * @author 肖舒翔
 * @version 1.0
 *
 */
@SpringBootApplication
@MapperScan("com.znck.mapper")
@EnableScheduling
public class ZnckApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZnckApplication.class, args);
    }
}
