package com.kejin.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaProApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProApplication.class, args);
	}

}
