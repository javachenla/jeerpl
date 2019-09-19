package com.example.demo.bean;

import java.io.Serializable;

public class ActiveList  implements Serializable{
	
	private String id;	 
	private String create_date;	 
	private String title;	 
	private String starttime;	 
	private String endtime;	 
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ActiveList(String id, String create_date, String title, String starttime, String endtime, String content) {
		super();
		this.id = id;
		this.create_date = create_date;
		this.title = title;
		this.starttime = starttime;
		this.endtime = endtime;
		this.content = content;
	}
	public ActiveList() {
		super();
	}	 

	 
}

