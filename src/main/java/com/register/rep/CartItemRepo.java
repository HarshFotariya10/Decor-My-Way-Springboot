package com.register.rep;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.register.entity.Cart;
import com.register.entity.CartItem;
import com.register.entity.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>,CrudRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    // Add more query methods if needed
}
