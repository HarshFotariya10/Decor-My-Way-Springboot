package com.register.rep;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.register.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> ,CrudRepository<Order, Long> {
	Order findByEntityId(Integer userId);
}
