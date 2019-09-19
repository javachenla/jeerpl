package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.ActiveList;

@Mapper
public interface ActiveListDao {
 
    List<ActiveList> selectAll();

}