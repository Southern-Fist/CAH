package com.onegirloneguy.controller.vo;

public abstract class BaseVO {

	private String errorMsg;
	
	public abstract void populate(Object obj);
	public abstract Object buildDomainObject();
	
	
	public void setErrorMessage(String msg){
		
		errorMsg = msg;
	}
	
	public String getErrorMessage(){
		
		return errorMsg;
	}
}
