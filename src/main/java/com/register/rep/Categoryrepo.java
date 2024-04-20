package com.register.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.register.entity.Category;


public interface Categoryrepo extends JpaRepository<Category,Integer> {
	@Query("Select c from Category c where c.categoryname like :key")
	List<Category> findbyCategorynameContaining(@Param("key") String categoryname);
}
