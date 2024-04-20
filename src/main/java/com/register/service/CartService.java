package com.register.service;

import java.util.List;

import com.register.Dto.CartDto;


public interface CartService {

	List<CartDto> viewCart(Integer userId);

	void addToCart(Integer userId, Integer productId);

	void reduceQuantity(Integer userId, Integer productId);
	 public int countProductsInUserCart(Integer userId);
	

}
