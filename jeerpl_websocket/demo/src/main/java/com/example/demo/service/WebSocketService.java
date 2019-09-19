package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendTopicMessage(String dest,Map<String, String> map) throws InterruptedException{
	
			template.convertAndSend(dest, map);
		
	}
	
	
}
