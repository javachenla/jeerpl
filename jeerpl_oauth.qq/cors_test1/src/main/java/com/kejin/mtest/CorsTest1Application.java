package com.kejin.mtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.kejin.mtest.filter"})
public class CorsTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(CorsTest1Application.class, args);
	}

}
