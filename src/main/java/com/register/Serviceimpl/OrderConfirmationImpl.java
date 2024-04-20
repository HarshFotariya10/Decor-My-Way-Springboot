package com.register.Serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.register.Dto.OrderConfirmationDTO;
import com.register.Dto.OrderConfirmationItemDTO;
import com.register.entity.Order;
import com.register.entity.OrderConfirmation;
import com.register.entity.OrderConfirmationItem;
import com.register.entity.OrderItem;
import com.register.rep.OrderConfirmationRepo;
import com.register.rep.OrderItemRepo;
import com.register.rep.OrderRepo;
import com.register.rep.Repo;
import com.register.service.OrderConfirmationService;




@Service
public class OrderConfirmationImpl implements OrderConfirmationService {

	@Value("${product_file_path}")
	private String filepPath;
	
	@Autowired
	private OrderRepo orderRepository;
	
	@Autowired
    private OrderItemRepo orderItemRepo; 
	
	@Autowired
	private Repo repo;
	
	@Autowired
	private OrderConfirmationRepo orderConfirmationRepository;
	
	@Override
	public Integer confirmOrderAndSaveToConfirmation(Long orderId, Integer userId, LocalDateTime confirmationDate,
	        String modeOfPayment) {
	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
	    
	    Optional<com.register.entity.Entity> userOptional = repo.findById(userId);
	    com.register.entity.Entity user = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

	    // Create a new OrderConfirmation entity
	    OrderConfirmation orderConfirmation = new OrderConfirmation();
	    orderConfirmation.setUserId(userId);
	    orderConfirmation.setConfirmationDate(confirmationDate);
	    orderConfirmation.setModeOfPayment(modeOfPayment);
	    
	    orderConfirmation.setAddress(user.getAddress());

	    // Transfer order items to OrderConfirmation and calculate total amount
	    double totalAmount = 0.0;
	    for (OrderItem orderItem : order.getOrderItems()) {
	        OrderConfirmationItem confirmationItem = new OrderConfirmationItem();
	        confirmationItem.setOrderConfirmation(orderConfirmation);
	        confirmationItem.setProduct(orderItem.getProduct());
	        confirmationItem.setQuantity(orderItem.getQuantity());

	        orderConfirmation.getConfirmationItems().add(confirmationItem);

	        totalAmount += orderItem.getProduct().getPrice() * orderItem.getQuantity();
	    }
	    orderConfirmation.setTotalAmount(totalAmount);

	    // Save OrderConfirmation entity
	    OrderConfirmation savedOrderConfirmation = orderConfirmationRepository.save(orderConfirmation);

	    // Delete order items
	    orderItemRepo.deleteAllByOrder(order);
	    
	    return savedOrderConfirmation.getId();
	}
	
	
	
	//Get All Orders
	@Override
    public List<OrderConfirmationDTO> getAllOrders() {
        List<OrderConfirmation> orders = orderConfirmationRepository.findAll();
        return orders.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
	@Override
	public List<OrderConfirmationDTO> getAllOrdersOfUser(Integer userId) {
		List<OrderConfirmation> orders = orderConfirmationRepository.findAll()
			                   .stream()
			                   .filter(order -> order.getUserId().equals(userId))
			                   .collect(Collectors.toList());
			return orders.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
	}
    
    private OrderConfirmationDTO mapToDto(OrderConfirmation order) {
    	 Optional<com.register.entity.Entity> userOptional = repo.findById(order.getUserId());
 	    com.register.entity.Entity user = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));
        OrderConfirmationDTO dto = new OrderConfirmationDTO();
        
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setConfirmationDate(order.getConfirmationDate());
        dto.setModeOfPayment(order.getModeOfPayment());
        dto.setAddress(order.getAddress());
        dto.setUsername(user.getName());
        
        // Assuming OrderConfirmationItemDTO is already mapped
        dto.setConfirmationItems(order.getConfirmationItems().stream()
                                    .map(item -> mapItemToDto(item))
                                    .collect(Collectors.toList()));
        return dto;
    }

    // You may also need a method to map OrderConfirmationItem to OrderConfirmationItemDTO
    private OrderConfirmationItemDTO mapItemToDto(OrderConfirmationItem item) {
        OrderConfirmationItemDTO dto = new OrderConfirmationItemDTO();
        dto.setId(item.getId());
        dto.setOrderConfirmationId(item.getOrderConfirmation().getId());
        dto.setProductId(item.getProduct().getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setProductname(item.getProduct().getProductname());
        dto.setProductImage(filepPath + item.getProduct().getImage());
    
        return dto;
    }
    
    @Override
    public OrderConfirmationDTO getOrderConfirmationById(Integer confirmationId) {
        OrderConfirmation orderConfirmation = orderConfirmationRepository.findById(confirmationId)
                .orElseThrow(() -> new IllegalArgumentException("Order confirmation not found with ID: " + confirmationId));

        return mapToDto(orderConfirmation);
    }






		

}
