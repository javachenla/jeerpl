package com.kejin.mtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cors1")
public class CorsController {
 
    @RequestMapping("/test")
    public String  test(){
    	return "this is cors1";
    }
   
}