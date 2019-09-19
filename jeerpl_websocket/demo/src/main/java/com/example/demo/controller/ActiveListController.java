package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ActiveList;
import com.example.demo.service.ActiveListService;
import com.example.demo.util.PageBean;
import com.example.demo.util.RedisUtil;

@RestController
@RequestMapping("/activeList")
public class ActiveListController {

    @Autowired
    private ActiveListService activeListService;
    
	@Autowired
	private RedisUtil redisUtil;

    
    @RequestMapping("/getAll")
    public List<ActiveList> getAllActiveList(){
        List<ActiveList> list = activeListService.getAllActiveList();
        return list;
    }
    
    @RequestMapping("/getAllByPage")
    public  PageBean<ActiveList> findItemByPage(int currentPage,int pageSize){
        return activeListService. findItemByPage(currentPage,  pageSize);
    }
    
}

