package com.kejin.mytest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kejin.mytest.bean.ActiveList;
import com.kejin.mytest.bean.ResultData;
import com.kejin.mytest.service.ActiveListService;

@RestController
@RequestMapping("/activelist")
public class ActiveListController {
	
    @Autowired
    private ActiveListService activeListService;

    @RequestMapping("/selectAll")
    public List<ActiveList>   selectAll(){
    	return activeListService.selectAll();
    }
    
    @RequestMapping("/selectbyId")
    public ResultData   selectbyId(String id){
    	 ResultData resData=new ResultData();
    	 resData.setResultSuccess(activeListService.selectbyId(id));
    	 return resData;
    }
    
    @RequestMapping("/selectAllByPage")
    public ResultData selectAllByPage(int currentPage,int pageSize){
    	
    	 ResultData resData=new ResultData();
    	 resData.setResultSuccess(activeListService.selectAllByPage(currentPage,  pageSize).getItems());
    	 return resData;
    }
    
}