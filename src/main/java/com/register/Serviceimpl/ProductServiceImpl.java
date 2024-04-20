package com.register.Serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.register.Dto.ProductDto;
import com.register.Exceptions.ResourceNotFoundException;
import com.register.entity.Category;
import com.register.entity.Product;
import com.register.rep.Categoryrepo;
import com.register.rep.ProductRepo;

import com.register.service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productrepo;
	
	private final ModelMapper modalmapper = new ModelMapper();

	@Autowired
	private Categoryrepo categoryrepo;
	
	  @PersistenceContext
	  private EntityManager entityManager;
	  
		@Value("${product_file_path}")
		private String filepPath;
		
	

	

	
	@Value("${product.image.product}")
	private String path;
	
	//CREATE PRODUCT
	@Override
	public ProductDto CreateProduct(ProductDto productdto,Integer CategoryId) {
		 
		
		Category category =  this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", CategoryId));
		Product product = this.modalmapper.map(productdto, Product.class);
		
		product.setCategory(category);
		
		Product newproduct = this.productrepo.save(product);
		
		return this.modalmapper.map(newproduct, ProductDto.class);
	}


	@Override
	public ProductDto UpdateProduct(ProductDto productdto,@RequestParam(value = "image", required = false) MultipartFile image, Integer productId) throws IOException {
		Product product =this.productrepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ProductId", productId));
		product.setProductname(productdto.getProductname());
		product.setDescription(productdto.getDescription());
		product.setPrice(productdto.getPrice());
		product.setImage(productdto.getImage());
	
			
		Product updatedProduct = this.productrepo.save(product);
		return this.modalmapper.map(updatedProduct, ProductDto.class);
	}

	
	
	
	//Get All Product
	@Override
	public List<ProductDto> GetAllProduct() {
		List<Product> allproducts = this.productrepo.findAll();	
		List<ProductDto> allproduct = allproducts.stream().map(product->this.modalmapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return allproduct; 	
	}

	
	// GET PRODUCT BY PRODUCT ID
	@Override
	public ProductDto GetProductbyId(Integer productId) {
		
	Product product =	this.productrepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ProductId", productId));	
		return this.modalmapper.map(product, ProductDto.class);
	}
	
	

	//GET PRODUCT BY CATEGORY ID
	@Override
	public List<ProductDto> GetallProductbyCategory(Integer categoryid) {
		
		Category cat = this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "Category", categoryid));
		List<Product> products =  this.productrepo.findByCategory(cat);
		List<ProductDto> productdto =products.stream().map(product-> this.modalmapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productdto;
	}
	
	
	
	@Override
	public List<ProductDto> GetProductByKeyword(String Keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//Delete
	@Override
	public void deleteProduct(Integer productId) {
		Product product =this.productrepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ProductId", productId));
		this.productrepo.delete(product);
		
	}


	@Override
	public List<ProductDto> searchproduct(String keyword) {
	List<Product> products =	this.productrepo.findbyproductnameContaining("%"+keyword+"%");
	List<ProductDto> productsdto = products.stream().map((product)-> this.modalmapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productsdto;
	}
	
	//Related Product
	  @Override
	    public List<ProductDto> getProductsWithSameCategoryExceptGivenProductId(Integer productId) {
	        // Retrieve the product with the given product ID
	        Product product = entityManager.find(Product.class, productId);
	        if (product == null) {
	            throw new ResourceNotFoundException("Product", "ProductId", productId);
	        }

	        // Get the category of the given product
	        Category category = product.getCategory();

	        // Query to get products with the same category except for the given product ID
	        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category = :category AND p.id != :productId");
	        query.setParameter("category", category);
	        query.setParameter("productId", productId);
	        List<Product> productList = query.getResultList();

	        // Convert products to ProductDto and return
	  
	        	    return productList.stream()
	        	            .map(productDto -> {
	        	                ProductDto dto = modalmapper.map(productDto, ProductDto.class);
	        	                dto.setImage(filepPath + productDto.getImage());
	        	                return dto;
	        	            })
	        	            .collect(Collectors.toList());
	    }

	

	


	

}
