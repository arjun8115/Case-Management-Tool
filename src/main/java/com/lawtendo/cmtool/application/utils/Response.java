package com.lawtendo.cmtool.application.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


@Component
public class Response {

	private String statusCode;
	
	private String message;
	
	private Object data;
	
	@Autowired
	private Gson gson;
		
//	public Response(String statusCode, String message,Object data) {
//		this.statusCode = statusCode;
//		this.message = message;
//		this.data = data;
//	}
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String toJson() {
		
		return gson.toJson(data);
		 
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
	
}