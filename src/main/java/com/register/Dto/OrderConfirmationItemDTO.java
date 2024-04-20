package com.register.Dto;

public class OrderConfirmationItemDTO {
	private Long id;
    private Integer orderConfirmationId;
    private Integer productId;
    private String Productname;
    private String productImage;
    private int quantity;
    private Long  totalQuantity;
    

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderConfirmationId() {
        return orderConfirmationId;
    }

    public void setOrderConfirmationId(Integer orderConfirmationId) {
        this.orderConfirmationId = orderConfirmationId;
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

	public String getProductname() {
		return Productname;
	}

	public void setProductname(String productname) {
		Productname = productname;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
    

}
