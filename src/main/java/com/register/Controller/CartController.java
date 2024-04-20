package com.register.Controller;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.Dto.CartDto;

import com.register.Exceptions.ApiResponse;
import com.register.Exceptions.ResourceNotFoundException;
import com.register.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
public class CartController {
	
    @Autowired
    private CartService cartService;
    
    @PostMapping("/add/{userId}/{ProductId}")
    public ResponseEntity<ApiResponse>  addToCart(@PathVariable Integer userId, @PathVariable Integer ProductId) {
        try {
            cartService.addToCart(userId, ProductId);
            ApiResponse response = new ApiResponse("Product added to cart successfully", true);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = new ApiResponse("Product quantity not available", false);
            return ResponseEntity.badRequest().body(response);
        } catch (ResourceNotFoundException e) {
            ApiResponse response = new ApiResponse("User or product not found", false);
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<CartDto>> viewCart(@PathVariable Integer userId) {
        List<CartDto> cartDTOs = cartService.viewCart(userId);
        List<CartDto> cartdtosList = new ArrayList<>();
//		List<CategoryDto> categoryList = new ArrayList<>();
//		for(CategoryDto category : categories) {
//			category.setImagename(filepPath + category.getImagename());
//			categoryList.add(category);
//		}
        return new ResponseEntity<List<CartDto>>(cartDTOs, HttpStatus.OK);
    }
    
    @GetMapping("/cart/count/{userId}")
    public int countProductsInUserCart(@PathVariable Integer userId) {
        // Call the service method to get the count of products in the user's cart
        int productCount = cartService.countProductsInUserCart(userId);
        return productCount;
    }

    
    @PostMapping("/reduceQuantity/{userId}/{productId}")
    public ResponseEntity<ApiResponse> reduceQuantity(@PathVariable Integer userId, @PathVariable Integer productId) {
        try {
            cartService.reduceQuantity(userId, productId);
            ApiResponse response = new ApiResponse("Quantity reduced successfully.", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse errorresponse = new ApiResponse("Failed To  reduced .", false);
            return ResponseEntity.badRequest().body(errorresponse);
        } 	
    }

}
