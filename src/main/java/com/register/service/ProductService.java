package com.register.service;



import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.register.Dto.ProductDto;



public interface ProductService{
	
	//Create 
	
	ProductDto CreateProduct(ProductDto productdto , Integer categoryid);
	
	//Update
	
	ProductDto UpdateProduct(ProductDto productdto, MultipartFile image, Integer productId) throws IOException;
	
	//Delete
	
	void deleteProduct(Integer productId);
	
	//Get All Product 
	
	List<ProductDto> GetAllProduct();
	
	//Get singleProduct
	
	ProductDto GetProductbyId(Integer productId);
	
	
	//Get All Product by  Category
	
	List<ProductDto> GetallProductbyCategory(Integer categoryid);
	
	
	//Search ProductBy Keyword
	
	List<ProductDto> GetProductByKeyword(String Keyword);
	
	//Search  Product
	List<ProductDto> searchproduct(String Keyword);

	public List<ProductDto> getProductsWithSameCategoryExceptGivenProductId(Integer productId);
	
	
	


	
	

}
