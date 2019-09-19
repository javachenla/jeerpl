package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/MyTest")
public class MyTestController {
	
	@RequestMapping(value = "/test")
	public String hello() {
		return "HelloWorld";
	}
	
}
