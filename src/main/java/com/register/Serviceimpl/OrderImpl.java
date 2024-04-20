package com.register.Serviceimpl;




import java.util.Iterator;
import java.util.List;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.register.Dto.OrderDto;
import com.register.Dto.OrderItemDto;
import com.register.entity.Cart;
import com.register.entity.CartItem;
import com.register.entity.Entity;
import com.register.entity.Order;
import com.register.entity.OrderItem;
import com.register.entity.Product;
import com.register.rep.CartRepo;
import com.register.rep.OrderItemRepo;
import com.register.rep.OrderRepo;
import com.register.rep.ProductRepo;
import com.register.rep.Repo;
import com.register.service.OrderService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service

public class OrderImpl implements OrderService {
	

    @Autowired
    private OrderRepo orderRepository;
	
	@Autowired
    private  CartRepo cartRepository;
	
	@Autowired
    private  Repo entityRepository;
	
	@Autowired
    private OrderItemRepo orderItemRepo; 
	
	@Autowired
	private ProductRepo productRepository;
	
	@Value("${product_file_path}")
	private String filepPath;
	

    
    
    
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Order placeOrder(Long cartId, Integer userId) {
        // Retrieve cart and user
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        Entity user = entityRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Find existing order or create a new one
        Order order = orderRepository.findByEntityId(userId);
        if (order != null) {
            // Remove existing order items
        	orderItemRepo.deleteAllByOrder(order);
        } else {
            order = new Order();
            order.setEntity(user);
        }

        // Create new order items from cart items
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            order.getOrderItems().add(orderItem);
        }

        // Save the updated or new order
        Order savedOrder = orderRepository.save(order);

        // Remove the cart after order is placed
        

        return savedOrder;
    }



	public List<OrderDto> getAllOrders() {
	    List<Order> orders = orderRepository.findAll();
	    return orders.stream()
	                 .map(order -> {
	                     OrderDto orderDTO = new OrderDto();
	                     orderDTO.setOrderId(order.getOrderId());
	                     orderDTO.setUser(order.getEntity().getId()); // Assuming getUserId returns the ID of the Entity/User
	                     // Set other properties if needed
	                     return orderDTO;
	                 })
	                 .collect(Collectors.toList());
	}

	@Override
	public List<OrderItemDto> getOrderItemsByUserId(Integer userId) {
	       List<OrderItem> orderItems = orderItemRepo.findByOrderEntityId(userId);
	       
	        return orderItems.stream()
	                         .map(this::convertToDTO)
	                         .collect(Collectors.toList());
	    }
		

	    private OrderItemDto convertToDTO(OrderItem orderItem) {
	        OrderItemDto dto = new OrderItemDto();
	        dto.setOrderItemId(orderItem.getOrderItemId());
	        dto.setOrderId(orderItem.getOrder().getOrderId());
	        dto.setProductId(orderItem.getProduct().getProductId());
	        dto.setQuantity(orderItem.getQuantity());
	        dto.setProductname(orderItem.getProduct().getProductname());
	        dto.setProductImage(filepPath + orderItem.getProduct().getImage());
	        dto.setProductprice(orderItem.getProduct().getPrice());
	        
	        // Set other fields as needed
	        return dto;
	    }
	    
	    @Override
	    @Transactional
	    public Order addSingleProductToOrder(Integer userId, Integer productId) {
	        // Retrieve user
	        Entity user = entityRepository.findById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("User not found"));

	        // Find existing order or create a new one
	        Order order = orderRepository.findByEntityId(userId);
	        if (order != null) {
	            // Remove existing order items
	            orderItemRepo.deleteAllByOrder(order);
	        } else {
	            order = new Order();
	            order.setEntity(user);
	        }

	        // Create new order item for the single product
	        OrderItem orderItem = new OrderItem();
	        orderItem.setOrder(order);
	        
	        // Retrieve product
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

	        // Set order item details
	        orderItem.setProduct(product);
	        orderItem.setQuantity(1); // Default quantity

	        // Add the order item to the order
	        order.getOrderItems().add(orderItem);

	        // Save the updated or new order
	        Order savedOrder = orderRepository.save(order);

	        return savedOrder;
	    }
	    
	    
}



	

