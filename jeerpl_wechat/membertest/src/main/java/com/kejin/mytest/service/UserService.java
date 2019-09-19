package com.kejin.mytest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kejin.mytest.bean.User;
import com.kejin.mytest.mapper.UserMapper;

import lombok.Synchronized;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
	@Transactional(readOnly = false)
    public  void insertUser(User user){
    	  userMapper.insertUser(user);
    }
 
    
    public  User  loginUser(User user){
          return userMapper.loginUser(user);
    }

    @Synchronized
	@Transactional(readOnly = false)
	    public  Integer  clearOpenId(String openId){
	    return userMapper.clearOpenId(openId);
    }
    
}