package com.register.entity;





import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity

@Table(name = "Category")
public class Category {
	
	@Id
	private int categoryID;
	@Column(unique = true,nullable = false)
	private String categoryname;
	private String categorytype;
	private String imagename;
	
	@OneToMany(mappedBy = "category",cascade = jakarta.persistence.CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
	


	public Category(int categoryID, String categoryname, String categorytype, String imagename,
			List<Product> products) {
		super();
		this.categoryID = categoryID;
		this.categoryname = categoryname;
		this.categorytype = categorytype;
		this.imagename = imagename;
		this.products = products;
	}





	public Category() {
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
	
	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}





	public String getImagename() {
		return imagename;
	}




	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	

	
	}


	
	
	
	
	
	


