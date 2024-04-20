package com.register.Exceptions;




public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String resourceField;
	Integer fieldvalue;
	
	
	public ResourceNotFoundException(String resourceName, String resourceField, Integer productId) {
		super(String.format("%s Not Found With %s :%s",resourceName,resourceField,productId));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.fieldvalue = productId;
		
	}


	public ResourceNotFoundException() {
		super();
	}








	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getResourceField() {
		return resourceField;
	}


	public void setResourceField(String resourceField) {
		this.resourceField = resourceField;
	}


	public Integer getFieldvalue() {
		return fieldvalue;
	}


	public void setFieldvalue(Integer fieldvalue) {
		this.fieldvalue = fieldvalue;
	}


	@Override
	public String toString() {
		return "ResourceNotFoundException [resourceName=" + resourceName + ", resourceField=" + resourceField
				+ ", fieldvalue=" + fieldvalue + "]";
	}
	
	
	
	

	
}
