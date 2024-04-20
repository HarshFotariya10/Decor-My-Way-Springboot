package com.register.Dto;

public class OrderItemDto {
    private Long orderItemId;
    private Long orderId;
    private Integer productId;
    private int quantity;
    private String productname;
    private String ProductImage;
    private Long productprice;
    

	




	public OrderItemDto(Long orderItemId, Long orderId, Integer productId, int quantity, String productname,
			String productImage, Long productprice) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.productname = productname;
		ProductImage = productImage;
		this.productprice = productprice;
	}


	public OrderItemDto() {
		super();
	}


	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getProductImage() {
		return ProductImage;
	}


	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}


	public Long getProductprice() {
		return productprice;
	}


	public void setProductprice(Long productprice) {
		this.productprice = productprice;
	}
	
	
	
    
}
