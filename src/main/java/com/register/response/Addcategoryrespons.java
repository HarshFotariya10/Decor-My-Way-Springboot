package com.register.response;

public class Addcategoryrespons {
	String message;
	Boolean status;
	
	
	public Addcategoryrespons(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}


	public Addcategoryrespons() {
		super();
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Addcategoryrespons [message=" + message + ", status=" + status + "]";
	}
	
	
	
}
