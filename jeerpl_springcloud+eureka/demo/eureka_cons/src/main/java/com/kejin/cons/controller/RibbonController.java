package com.kejin.cons.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
 
@RestController
public class RibbonController {
 
    @Autowired
    RestTemplate restTemplate;
 
    @GetMapping("/getPortInfo")
    public String getPoducerInfo() {
    	String result = this.restTemplate.getForObject("http://service-eureka-clienteureka/getPortInfo", String.class);
        return result;
    }
}