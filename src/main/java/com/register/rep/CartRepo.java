package com.register.rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{

}
