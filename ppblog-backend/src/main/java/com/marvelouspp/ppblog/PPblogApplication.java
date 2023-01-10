package com.marvelouspp.ppblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@MapperScan("com.marvelouspp.ppblog.mapper")
@EnableScheduling
public class PPblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PPblogApplication.class, args);
	}

}
