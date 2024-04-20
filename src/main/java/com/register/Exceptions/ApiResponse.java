package com.register.Exceptions;


public class ApiResponse {
	
	private String message;
	
	private Boolean succes;
	
	public ApiResponse(String message, Boolean succes) {
		super();
		this.message = message;
		this.succes = succes;
	}

	public ApiResponse() {
		super();
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSucces() {
		return succes;
	}

	public void setSucces(Boolean succes) {
		this.succes = succes;
	}
	

	
}
