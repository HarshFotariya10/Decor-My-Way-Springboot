package com.register.Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.Dto.CategoryDto;

import com.register.Exceptions.ApiResponse;

import com.register.service.CategoryService;
import com.register.service.FileService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
public class CategoryController {

	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private FileService fileservice;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${category_dir_path}")
	private String path;
	
	@Value("${category_file_path}")
	private String filepPath;
	
	//Create
	@PostMapping("/addCategory") 
	public ResponseEntity<CategoryDto> CreateCategory(@RequestParam("data") String Categorydto,@RequestParam("image") MultipartFile image) throws IOException
	{
		CategoryDto updated = mapper.readValue(Categorydto, CategoryDto.class);
		String filename = 	this.fileservice.UploadImage(path , image);
		updated.setImagename(filename);		
		CategoryDto createcategory =  this.categoryservice.CreateCategory(updated);
		return new ResponseEntity<CategoryDto>(createcategory,HttpStatus.CREATED);
	}		

	
	
	//Update
		@PostMapping("/update/{catid}")
		public ResponseEntity<CategoryDto> UpdateCategory(@PathVariable Integer catid,@RequestParam("data") String categoryDtoData,@RequestParam(value = "image", required = false) MultipartFile image) throws IOException
		{
		      CategoryDto categoryDto = mapper.readValue(categoryDtoData, CategoryDto.class);
	
		      String filename = this.fileservice.UploadImage(path , image);
		      categoryDto.setImagename(filename);	
		      CategoryDto updatedCategory = categoryservice.UpdateCategory(categoryDto, image, catid);
			return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
				
		}
	
	//Delete
	
	@DeleteMapping("/{catid}")
	public ResponseEntity<ApiResponse> DeleteCategory(@PathVariable Integer catid)
	{
		  this.categoryservice.deletecategory(catid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Succesfully", true),HttpStatus.OK);
			 
	}
	
	//Get Category by id
	
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDto> Getcategory(@PathVariable Integer catid)
	{
		 CategoryDto categorydto =	 this.categoryservice.GetCategory(catid);
		 return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.OK);
		
	}
	
	//Get All Category
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> Getcategories()
	{
		List<CategoryDto> categories =	this.categoryservice.getcategories();
		
		List<CategoryDto> categoryList = new ArrayList<>();
		for(CategoryDto category : categories) {
			category.setImagename(filepPath + category.getImagename());
			categoryList.add(category);
		}
		
		return new ResponseEntity<List<CategoryDto>>(categoryList,HttpStatus.OK);
	}
	
	//Get Category Of Furniture 
	@GetMapping("/furniture")
	public ResponseEntity<List<CategoryDto>> getFurnitureCategories() {
	    List<CategoryDto> furnitureCategories = this.categoryservice.getFurnitureCategories();
	    List<CategoryDto> categoryList = new ArrayList<>();
		for(CategoryDto category : furnitureCategories) {
			category.setImagename(filepPath + category.getImagename());
			categoryList.add(category);
		}
	    return new ResponseEntity<List<CategoryDto>>(categoryList, HttpStatus.OK);
	}
	
	//Get Category Of Decor Items
	@GetMapping("/decoritems")
	public ResponseEntity<List<CategoryDto>> getDecorItemsCategory() {
	    List<CategoryDto> decoritemsCategories = this.categoryservice.getDecorItems();
	    List<CategoryDto> decorIremsList = new ArrayList<>();
		for(CategoryDto category : decoritemsCategories) {
			category.setImagename(filepPath + category.getImagename());
			decorIremsList.add(category);
		}
	    return new ResponseEntity<List<CategoryDto>>(decorIremsList, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<CategoryDto>> SearchProductByTitle(@PathVariable("keyword") String Keywords){
		List<CategoryDto> result =this.categoryservice.searchcategory(Keywords);
	    List<CategoryDto> resultlist = new ArrayList<>();
		for(CategoryDto category : result) {
			category.setImagename(filepPath + category.getImagename());
			resultlist.add(category);
		}
		return new ResponseEntity<List<CategoryDto>>(resultlist,HttpStatus.OK);
	}
	
//	//Image Upload of Category
//	@PostMapping("/image/upload/{catid}")
//	public ResponseEntity<CategoryDto> uploadimage(@RequestParam("image") MultipartFile image,
//			@PathVariable Integer catid) throws IOException{
//		CategoryDto categorydto = this.categoryservice.GetCategory(catid);
//		String filename = 	this.fileservice.UploadImage(path , image);
//		categorydto.setImagename(filename);
//		CategoryDto updatedpost =this.categoryservice.UpdateCategory(categorydto, catid);
//		return new ResponseEntity<CategoryDto>(updatedpost,HttpStatus.OK);	
//	}
	
	
 


	

}
