package com.register.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.register.Dto.LoginDto;
import com.register.Dto.UserDto;
import com.register.entity.Entity;

import com.register.response.LoginResponse;

import com.register.service.Service;




@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
public class Controller {
	
	@Autowired
	private Service service;
	@PostMapping("/addregister")
	public Entity addregister(@RequestBody Entity entity) {
		return service.add(entity);
	}
	
	

	@GetMapping("/getemail/{email}")
	public ResponseEntity<UserDto> getemail(@PathVariable String email) {
		 UserDto userDTO = service.getByEmail(email);
		 return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping("/login")
	private ResponseEntity<?> loginEmployee(@RequestBody LoginDto logindto)
	{
		LoginResponse loginresponse = service.loginresponse(logindto);
		return ResponseEntity.ok(loginresponse);
		
		
	}

}
