package com.register.Dto;

public class CategoryDto {

	private int categoryID;
	
	private String categoryname;
	
	private String categorytype;
	
	private String imagename;
	
	
	





	public CategoryDto(int categoryID, String categoryname, String categorytype, String imagename) {
		super();
		this.categoryID = categoryID;
		this.categoryname = categoryname;
		this.categorytype = categorytype;
		this.imagename = imagename;
	}


	public CategoryDto() {
		super();
	}


	public int getCategoryID() {
		return categoryID;
	}


	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	public String getCategoryname() {
		return categoryname;
	}


	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	

	

	public String getCategorytype() {
		return categorytype;
	}


	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}


	@Override
	public String toString() {
		return "CategoryDto [categoryID=" + categoryID + ", categoryname=" + categoryname + ", categorytype="
				+ categorytype + ", imagename=" + imagename + "]";
	}


	
	
	

}
