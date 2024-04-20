package com.register.Dto;

public class CartItemDto {

    private Integer productId;

    private int quantity;
    private long price;
	private String productname;
	private String imagename;
	
    public CartItemDto(Integer productId, int quantity, long price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		
	}

	public CartItemDto() {
		super();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	
    
    
    
}
