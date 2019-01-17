package com.znck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.znck.service.RunService;

@SpringBootApplication
@MapperScan("com.znck.mapper")
public class ZnckApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZnckApplication.class, args);
	}
}

