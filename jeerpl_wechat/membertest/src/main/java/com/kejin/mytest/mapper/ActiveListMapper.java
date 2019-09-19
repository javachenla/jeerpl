package com.kejin.mytest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kejin.mytest.bean.ActiveList;

@Mapper
public interface ActiveListMapper {
 
    List<ActiveList> selectAll();

    List<ActiveList> selectbyId(String id);
}