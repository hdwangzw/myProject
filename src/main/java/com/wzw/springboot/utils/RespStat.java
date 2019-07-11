package com.wzw.springboot.utils;

public class RespStat {
	
	private String mesg;//消息
	private String code;//状态码   成功：200  失败：500
	
	private static RespStat INSTANCE;
	
	private RespStat() {};
	
	public static RespStat getInstance() {
		return INSTANCE = new RespStat();
	}
	
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public RespStat bulid(String code,String mesg) {
		this.mesg = mesg;
		this.code = code;
		return this;
	}
}
