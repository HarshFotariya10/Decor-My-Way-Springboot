package com.register.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.register.Dto.LoginDto;
import com.register.Dto.ProductDto;
import com.register.Dto.UserDto;
import com.register.entity.Entity;

import com.register.rep.Repo;

import com.register.response.LoginResponse;

@org.springframework.stereotype.Service
public class Service implements EmployeeService {
	
	@Autowired
	private Repo repo;
	
    @Autowired
    private ProductService productService;
    
	@Value("${product_file_path}")
	private String filepPath;
	
	public Entity add(Entity entity) {
		return repo.save(entity);
		
	}
	@Override
	public LoginResponse loginresponse(LoginDto logindto) {
		
		Entity entity = repo.findMyEmail(logindto.getEmail());
		if (entity != null) {
			String password = logindto.getPassword();
			String entitypassword = entity.getPassword();
			if (password.equals(entitypassword)) {	
				Optional<Entity> entity1 = repo.findOneByEmailAndPassword(logindto.getEmail(), logindto.getPassword());
				if (entity1.isPresent()) {
					return new LoginResponse("Login Succes", true);
				} else {
					return new LoginResponse("Login Failed", false);
				}
			} 
			else {
				return new LoginResponse("password not Match", false);
			}
		}
		else {
			return new LoginResponse("Email Not Exist", false);
		}
	}
	@Override
	public UserDto getByEmail(String email) {	
		Entity entity = repo.findByEmail(email);
		UserDto userDTO = new UserDto(); 
		userDTO.setId(entity.getId());	
		userDTO.setEmail(entity.getEmail());
		userDTO.setName(entity.getName());
		userDTO.setAddress(entity.getAddress());
		userDTO.setMoblieno(entity.getMoblieno());
		userDTO.setPincode(entity.getPincode());
		return userDTO;
		
	}
	
			
	public List<ProductDto> getRandomProducts() {
        List<ProductDto> randomProducts = new ArrayList<>();
        List<ProductDto> allProducts = productService.GetAllProduct();

        // Ensure we have at least three products in the database
        if (allProducts.size() < 3) {
            throw new RuntimeException("There are not enough products in the database.");
        }

        Random random = new Random();
        randomProducts.clear();

        // Randomly select three distinct products
        while (randomProducts.size() < 3) {
            ProductDto randomProduct = allProducts.get(random.nextInt(allProducts.size()));
            String imagePath = "http://localhost:8080/file/product/" + randomProduct.getImage();
            randomProduct.setImage(imagePath);
            if (!randomProducts.contains(randomProduct)) {
                randomProducts.add(randomProduct);
            }
        }

        return randomProducts;
    }
	 
		
		
}
	


