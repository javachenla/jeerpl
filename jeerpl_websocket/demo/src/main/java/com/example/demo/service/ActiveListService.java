package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ActiveList;
import com.example.demo.dao.ActiveListDao;
import com.example.demo.util.PageBean;
import com.github.pagehelper.PageHelper;


@Service
public class ActiveListService {
    
    @Autowired
    private ActiveListDao activeListDao;
   
    public List<ActiveList> getAllActiveList(){
         List<ActiveList> list = new ArrayList<ActiveList>();
         list = activeListDao.selectAll();
         return list;
    }
    
    public  PageBean<ActiveList> findItemByPage(int currentPage,int pageSize) {
    	 List<ActiveList> allItems = activeListDao.selectAll(); 
    	
    	int countNums = allItems.size();   
    	
    	//设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        
        allItems = activeListDao.selectAll();       //全部商品
        PageBean<ActiveList> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
    

}