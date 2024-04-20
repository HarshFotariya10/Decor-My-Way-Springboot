package com.register.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.register.entity.Category;
import com.register.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategory(Category category);
	
	@Query("Select p from Product p where p.productname like :key")
	List<Product> findbyproductnameContaining(@Param("key") String productname);

	

}
