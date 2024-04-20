package com.register.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.register.entity.OrderConfirmation;

public interface OrderConfirmationRepo extends JpaRepository<OrderConfirmation, Integer> ,CrudRepository<OrderConfirmation, Integer>{

	List<OrderConfirmation> findByUserId(Integer userId);

}
