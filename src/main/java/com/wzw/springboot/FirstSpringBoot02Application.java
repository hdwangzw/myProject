package com.wzw.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.wzw.springboot.mapper")
public class FirstSpringBoot02Application {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBoot02Application.class, args);
	}

}
