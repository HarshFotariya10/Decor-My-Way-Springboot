package com.register.service;

import java.time.LocalDateTime;
import java.util.List;

import com.register.Dto.OrderConfirmationDTO;
import com.register.entity.OrderConfirmation;

public interface OrderConfirmationService {

	public Integer confirmOrderAndSaveToConfirmation(Long orderId, Integer userId, LocalDateTime confirmationDate, String modeOfPayment);
	public List<OrderConfirmationDTO> getAllOrders();
	OrderConfirmationDTO getOrderConfirmationById(Integer confirmationId);
	List<OrderConfirmationDTO> getAllOrdersOfUser(Integer userId);
}