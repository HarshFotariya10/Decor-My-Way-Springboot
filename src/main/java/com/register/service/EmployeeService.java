package com.register.service;

import java.util.List;

import com.register.Dto.LoginDto;
import com.register.Dto.ProductDto;
import com.register.Dto.UserDto;

import com.register.response.LoginResponse;

public interface EmployeeService {

	LoginResponse loginresponse(LoginDto logindto);
	 
	UserDto getByEmail(String email);
	public List<ProductDto> getRandomProducts();
	
}
