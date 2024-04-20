package com.register.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.register.Dto.OrderDto;
import com.register.Dto.OrderItemDto;
import com.register.entity.Order;
import com.register.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
public class OrderController {
	
    @Autowired
    private OrderService orderService;
    


    @PostMapping("/place/{cartId}/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long cartId, @PathVariable Integer userId) {
        try {
            Order order = orderService.placeOrder(cartId, userId);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public List<OrderItemDto> getOrderItemsByUserId(@PathVariable("userId") Integer userId) {
    	
        return orderService.getOrderItemsByUserId(userId);
    }
    //Single Product
    @PostMapping("/addSingleProduct/{userId}/{productId}")
    public ResponseEntity<Order> addSingleProductToOrder(@PathVariable Integer userId, @PathVariable Integer productId) {
        try {
            Order order = orderService.addSingleProductToOrder(userId, productId);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
