package com.register.Dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long cartId;
    private Integer userId;
    private double totalamount;
    private List<CartItemDto> cartItems = new ArrayList<>();
	
    
    public CartDto(Long cartId, Integer userId, List<CartItemDto> cartItems) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.cartItems = cartItems;
	}

 
	public double getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}


	public CartDto() {
		super();
	}


	public Long getCartId() {
		return cartId;
	}


	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId2) {
		this.userId = userId2;
	}


	public List<CartItemDto> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
    
    
    
	
}
