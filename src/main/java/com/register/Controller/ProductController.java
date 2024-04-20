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

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.register.Dto.ProductDto;
import com.register.Exceptions.ApiResponse;
import com.register.service.FileService;
import com.register.service.ProductService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
@RequestMapping("/product/")

public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@Autowired	
	private FileService fileservice;
	
	@Autowired	
	private com.register.service.EmployeeService EmployeeService;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${product_dir_path}")
	private String path;
	
	@Value("${product_file_path}")
	private String filepPath;
	
	
	
	//Create
	@PostMapping("/category/{categoryID}/product")
	public ResponseEntity<ProductDto> CreateProduct(@RequestParam("data") String ProductDto,@RequestParam("image") MultipartFile image,@PathVariable Integer categoryID) throws IOException{
		ProductDto updated = mapper.readValue(ProductDto, ProductDto.class);
		String filename = 	this.fileservice.UploadImage(path , image);
		updated.setImage(filename);	
		ProductDto createpost =this.productservice.CreateProduct(updated, categoryID);
		return new ResponseEntity<ProductDto>(createpost,HttpStatus.CREATED);
		
	}
	
	//Get Product By Category
	@GetMapping("/category/{categoryID}")
	public ResponseEntity<List<ProductDto>> getProductbycategory(@PathVariable Integer categoryID){
		List<ProductDto> Products = this.productservice.GetallProductbyCategory(categoryID);
		List<ProductDto> productList = new ArrayList<>();
		for(ProductDto product : Products) {
			product.setImage(filepPath + product.getImage());
			productList.add(product);
		}
		
		return new ResponseEntity<List<ProductDto>>(productList, HttpStatus.OK);
	}	
	
//	@GetMapping("/")
//	public ResponseEntity<List<CategoryDto>> Getcategories()
//	{
//		List<CategoryDto> categories =	this.categoryservice.getcategories();
//		
//		List<CategoryDto> categoryList = new ArrayList<>();
//		for(CategoryDto category : categories) {
//			category.setImagename(filepPath + category.getImagename());
//			categoryList.add(category);
//		}
//		
//		return new ResponseEntity<List<CategoryDto>>(categoryList,HttpStatus.OK);
//	}
	
	//Get All Product
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getallproduct(){
	List<ProductDto> allProduct = this.productservice.GetAllProduct();
	List<ProductDto> productList = new ArrayList<>();
	for(ProductDto product : allProduct) {
		product.setImage(filepPath + product.getImage());
		productList.add(product);
	}
	
	return new ResponseEntity<List<ProductDto>>(productList,HttpStatus.OK);
	}
	
	// Get product by Id
	@GetMapping("/allproducts/{productId}")
	public ResponseEntity<ProductDto> getproductbyId(@PathVariable Integer productId ){
	ProductDto allProduct = this.productservice.GetProductbyId(productId);
	allProduct.setImage(filepPath+allProduct.getImage());
	return new ResponseEntity<ProductDto>(allProduct,HttpStatus.OK);
	}

	
	
	//UPDATE PRODUCT
	@PostMapping("/update/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId,@RequestParam("data") String productDtoData,@RequestParam(value = "image", required = false) MultipartFile image) throws IOException, JsonProcessingException{
		ProductDto productdto = new ObjectMapper().readValue(productDtoData, ProductDto.class);
		String filename = 	this.fileservice.UploadImage(path , image);
		productdto.setImage(filename);	
		ProductDto UpdatedProduct = productservice.UpdateProduct(productdto, image, productId);
		return new ResponseEntity<ProductDto>(UpdatedProduct,HttpStatus.OK);
		
	}
	
	
	
	//DELETE PRODUCT
	@DeleteMapping("/delete/{productID}")
	public ApiResponse deleteProduct(@PathVariable Integer productID)
	{
		this.productservice.deleteProduct(productID);
		return new ApiResponse("Product Succesfully Deleted",true);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<ProductDto>> SearchProductByTitle(@PathVariable("keyword") String Keywords){
		List<ProductDto> result =this.productservice.searchproduct(Keywords);
		List<ProductDto> productList = new ArrayList<>();
		for(ProductDto product : result) {
			product.setImage(filepPath + product.getImage());
			productList.add(product);
		}
		return new ResponseEntity<List<ProductDto>>(productList,HttpStatus.OK);
	}
	
	//Three Random
    @GetMapping("/random")
    public List<ProductDto> getRandomProducts() {
        return EmployeeService.getRandomProducts();
    }
    
    @GetMapping("/exclude/{productId}")
    public List<ProductDto> getProductsWithSameCategoryExceptGivenProductId(@PathVariable Integer productId) {
        return productservice.getProductsWithSameCategoryExceptGivenProductId(productId);
    }


	

}
