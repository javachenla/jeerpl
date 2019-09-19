package com.kejin.mytest.bean;

public class ResultData {
	
	public Integer rtnCode;
	public String msg;
	public Object data;
	
	public ResultData() {
		super();
	}

	public ResultData(Integer rtnCode, String msg, Object data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}

	// 返回错误，可以传msg
	public void setResultError(String msg) {
		this.rtnCode = 500;
		this.msg = msg;
		this.data = null;
	}

	// 返回成功，可以传data值
	public void setResultSuccess(Object data) {
		this.rtnCode = 200;
		this.msg = "success";
		this.data = data;
	}
	
	public Integer getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(Integer rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultData [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
