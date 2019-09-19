package cn.jbolt.wx.model;

import java.util.Map;

public class WxReurnMap {
	private  Integer isSucceed;  //1为成功   0为失败
	private  Map<String ,Object> data;
	private Integer optionCode; //操作码
	public WxReurnMap(Integer isSucceed, Map<String, Object> data, Integer optionCode) {
		super();
		this.isSucceed = isSucceed;
		this.data = data;
		this.optionCode = optionCode;
	}
	public Integer getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(Integer optionCode) {
		this.optionCode = optionCode;
	}
	public Integer getIsSucceed() {
		return isSucceed;
	}
	public void setIsSucceed(Integer isSucceed) {
		this.isSucceed = isSucceed;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public WxReurnMap(Integer isSucceed, Map<String, Object> data) {
		super();
		this.isSucceed = isSucceed;
		this.data = data;
	}
	public WxReurnMap() {
		super();
	}
	 
}
