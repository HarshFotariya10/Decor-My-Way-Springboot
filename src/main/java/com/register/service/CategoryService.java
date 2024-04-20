package com.register.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.register.Dto.CategoryDto;


public interface CategoryService {
	
	//Create
	 CategoryDto CreateCategory(CategoryDto categorydto);
	
	//Update 
	 CategoryDto UpdateCategory(CategoryDto categorydto,@RequestParam(value = "image", required = false) MultipartFile image,Integer CategoryId) throws IOException;
	
	//delete
	 void  deletecategory(Integer CategoryId);
	
	//get
	 List<CategoryDto> getcategories();

	CategoryDto GetCategory(Integer CategoryId);
	
	//Search
	List<CategoryDto> searchcategory(String Keyword);
	
	//Get Category Of Furniture Type
	public List<CategoryDto> getFurnitureCategories();
	
	//Get Category Of Decor Items
	public List<CategoryDto> getDecorItems();
	
	
	
	
	

}
