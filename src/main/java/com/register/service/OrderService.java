package com.register.service;


import java.util.List;

import com.register.Dto.OrderDto;
import com.register.Dto.OrderItemDto;
import com.register.entity.Order;


public interface OrderService {
	 
		

		Order placeOrder(Long cartId, Integer userId);

		List<OrderDto> getAllOrders();
		
		List<OrderItemDto> getOrderItemsByUserId(Integer userId);
		
		public Order addSingleProductToOrder(Integer userId, Integer productId);
}
