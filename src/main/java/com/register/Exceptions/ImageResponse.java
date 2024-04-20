package com.register.Exceptions;

public class ImageResponse {

	private String filename;
	private  String message;
	
	
	public ImageResponse(String filename, String message) {
		super();
		this.filename = filename;
		this.message = message;
	}


	
	public ImageResponse() {
		super();
	}



	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	 
}
