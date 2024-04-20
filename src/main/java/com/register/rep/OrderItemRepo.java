package com.register.rep;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.register.entity.Cart;
import com.register.entity.Order;
import com.register.entity.OrderItem;

import jakarta.transaction.Transactional;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> ,CrudRepository<OrderItem, Long> {

	List<OrderItem> findByOrderEntityId(Integer userId);
	@Transactional
    void deleteAllByOrder(Order order);
}
