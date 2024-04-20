package com.register.rep;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.register.entity.Entity;
@EnableJpaRepositories
public interface Repo extends CrudRepository<Entity,Integer>,JpaRepository<Entity,Integer>{
	
	 @Query("SELECT e FROM Entity e WHERE e.email = :email AND e.password = :password")
	Optional<Entity> findOneByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	 
	@Query("SELECT e FROM Entity e WHERE e.email = :email")
	 Entity findMyEmail(@Param("email") String email);
	
	Entity findByEmail(String email);
	
	
}


	