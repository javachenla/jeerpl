package com.kejin.mytest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kejin.mytest.bean.User;

@Mapper
public interface UserMapper {
    //插入用户信息
    Integer insertUser(User user);
    
    //清除openId
    Integer clearOpenId(String id);
	
	//用户登录 
	User loginUser(User user);

	 
}