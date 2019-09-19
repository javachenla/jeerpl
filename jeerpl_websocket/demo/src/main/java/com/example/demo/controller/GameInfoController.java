package com.example.demo.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.WebSocketService;
 
@Controller
public class GameInfoController {

	@Autowired
	private WebSocketService ws;
	
	@Scheduled(fixedRate=1000)
	@RequestMapping("/vsem") 
	@ResponseBody
	public void  gameInfo() throws InterruptedException{
		Map<String, String> map = new HashMap<String, String>();
		 int processors = Runtime.getRuntime().availableProcessors();
	       
		    Long freeMem = Runtime.getRuntime().freeMemory();
	        Long maxMem = Runtime.getRuntime().maxMemory();
	        String message = String.format("服务器可用处理器:%s; 虚拟机空闲内容大小: %s; 最大内存大小: %s", processors,freeMem,maxMem );
	
		map.put("content", message);
		ws.sendTopicMessage("/topic/game_chat",map);
	}
	
	public static void main(String[] args) {
		        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		        MemoryUsage usage = memoryMXBean.getHeapMemoryUsage();
		        System.out.println("INT HEAP:" + usage.getInit()/1024/1024 + "Mb");
		        System.out.println("MAX HEAP:" + usage.getMax()/1024/1024 + "Mb");
		        System.out.println("USED HEAP:" + usage.getUsed()/1024/1024 + "Mb");
		        
		        System.out.println("\nFull Information:");
		        System.out.println("Heap Memory Usage:" + memoryMXBean.getHeapMemoryUsage());
		        System.out.println("Non-Heap Memory Usage:" + memoryMXBean.getNonHeapMemoryUsage());
		        
		        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
		        System.out.println("=====================java options==================");
		        System.out.println(inputArguments);
		        
		        System.out.println("=====================通过java来获取相关系统状态====================");
		        long i = Runtime.getRuntime().totalMemory()/1024/1024;//Java 虚拟机中的内存总量，以字节为单位
		        System.out.println("总的内存量为:" + i + "Mb");
		        long j = Runtime.getRuntime().freeMemory()/1024/1024;//Java 虚拟机中的空闲内存量
		        System.out.println("空闲内存量:" + j + "Mb");
		        long k = Runtime.getRuntime().maxMemory()/1024/1024;
		        System.out.println("最大可用内存量:" + k + "Mb");
	}
}



