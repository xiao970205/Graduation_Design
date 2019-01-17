package com.znck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

import com.znck.service.RunService;

@SpringBootApplication
@MapperScan("com.znck.mapper")
@EnableScheduling
public class ZnckApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZnckApplication.class, args);
    }
}
