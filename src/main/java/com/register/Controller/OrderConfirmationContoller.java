package com.register.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.Dto.OrderConfirmationDTO;
import com.register.service.OrderConfirmationService;

@RestController
@RequestMapping("/Confirm")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
public class OrderConfirmationContoller {
	
	@Autowired
	private OrderConfirmationService orderConfirmationService;
	
	
	 @PostMapping("/confirm-order/{orderId}/{userId}/{modeOfPayment}")
	    public ResponseEntity<Long> confirmOrder(@PathVariable Long orderId, @PathVariable Integer userId, @PathVariable String modeOfPayment) {
	        try {
	            // Set confirmation date to the current date and time
	            LocalDateTime confirmationDate = LocalDateTime.now();
	            // Call the service method to confirm the order and save to confirmation
	           long Id = orderConfirmationService.confirmOrderAndSaveToConfirmation(orderId, userId, confirmationDate, modeOfPayment);

	            return ResponseEntity.ok(Id);
	        } catch (IllegalArgumentException ex) {
	            // Handle if order or user not found
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        } catch (Exception e) {
	            // Handle other exceptions
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	 
	 //Get All Order
	    @GetMapping("/all")
	    public ResponseEntity<List<OrderConfirmationDTO>> getAllOrders() {
	        List<OrderConfirmationDTO> orders = orderConfirmationService.getAllOrders();
	        return new ResponseEntity<>(orders, HttpStatus.OK);
	    }
	    //Get Order by Id
	    @GetMapping("/{confirmationId}")
	    public ResponseEntity<OrderConfirmationDTO> getOrderConfirmationById(@PathVariable Integer confirmationId) {
	        try {
	            OrderConfirmationDTO orderConfirmationDTO = orderConfirmationService.getOrderConfirmationById(confirmationId);
	            return ResponseEntity.ok(orderConfirmationDTO);
	        } catch (IllegalArgumentException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	    
	    @GetMapping("/orders/{userId}")
	    public List<OrderConfirmationDTO> getAllOrdersByUserId(@PathVariable Integer userId) {
	        return orderConfirmationService.getAllOrdersOfUser(userId);
	    }
	    
	

}
