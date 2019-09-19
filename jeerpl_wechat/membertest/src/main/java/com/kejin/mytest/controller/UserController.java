package com.kejin.mytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kejin.mytest.bean.ResultData;
import com.kejin.mytest.bean.User;
import com.kejin.mytest.service.UserService;
import com.kejin.mytest.util.RedisDao;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	RedisDao redisDao;
 
    @Autowired
    private UserService userService;

    @RequestMapping("/insertUser")
    public ResultData   insertUser(User user){
    	 userService.insertUser(user);
    	  
    	 ResultData resData=new ResultData();
    	 resData.setResultSuccess("注册成功");
    	 
    	 return resData;
    }
    
    @RequestMapping("/loginUser")
    public ResultData registerUser(User user){
    	User usr = userService.loginUser(user);
    	
    	 ResultData resData=new ResultData();
    	 resData.setResultSuccess(usr);
    	 
    	 return resData;
    } 
    
    @RequestMapping("/clearOpenId")
    public  ResultData  clearOpenId(String openId){
    	Integer num=userService.clearOpenId(openId);
	    ResultData resData=new ResultData();
    	 
    	if(num>0) {
    		resData.setResultSuccess("删除成功");
    	}else {
    		resData.setResultError("删除失败");
    	}
    	return resData;
    }
    
}