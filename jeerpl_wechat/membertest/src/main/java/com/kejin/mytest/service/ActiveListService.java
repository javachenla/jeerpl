package com.kejin.mytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kejin.mytest.bean.ActiveList;
import com.kejin.mytest.mapper.ActiveListMapper;
import com.kejin.mytest.util.PageBean;

@Service
public class ActiveListService {
    
    @Autowired
    private ActiveListMapper  activeListMapper;
    
    public List<ActiveList> selectAll(){
    	  return  activeListMapper.selectAll();
    }

    public List<ActiveList> selectbyId(String id){
    	 return  activeListMapper.selectbyId(id);
    }
     
	public PageBean<ActiveList> selectAllByPage(int currentPage, int pageSize) {
		
		 List<ActiveList> allItems = activeListMapper.selectAll(); 
	    	
	    	int countNums = allItems.size();   
	    	
	    	//设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
	        PageHelper.startPage(currentPage, pageSize);
	        
	        allItems = activeListMapper.selectAll();       //全部商品
	        PageBean<ActiveList> pageData = new PageBean<>(currentPage, pageSize, countNums);
	        pageData.setItems(allItems);
	        return pageData;
	}
    
}