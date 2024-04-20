package com.register.Serviceimpl;



import java.io.IOException;
import java.util.List;


import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.register.Exceptions.*;
import com.register.Dto.CategoryDto;
import com.register.entity.Category;
import com.register.rep.Categoryrepo;
import com.register.service.CategoryService;
import com.register.service.FileService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private Categoryrepo categoryrepo;
	
	@Autowired
	private FileService fileservice;
	
	
	@Value("${project.image}")
	private String path;
	
	
	private final ModelMapper modalmapper = new ModelMapper();

	
	@Override
	public CategoryDto CreateCategory(CategoryDto categorydto) {

	            Category category = modalmapper.map(categorydto, Category.class);
	            Category addedCategory = categoryrepo.save(category);
	            return modalmapper.map(addedCategory, CategoryDto.class);
	      
	        }

	@Override
	public CategoryDto UpdateCategory(CategoryDto categorydto,@RequestParam(value = "image", required = false) MultipartFile image, Integer CategoryId) throws IOException {
		Category Cat = this.categoryrepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",CategoryId));
		Cat.setCategoryname(categorydto.getCategoryname());
		
		Cat.setCategorytype(categorydto.getCategorytype());
		Cat.setImagename(categorydto.getImagename());


		Category Updatedcat = this.categoryrepo.save(Cat);
		return this.modalmapper.map(Updatedcat,CategoryDto.class);
	}

	@Override
	public void deletecategory(Integer CategoryId) {
		Category cat = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",CategoryId));
		this.categoryrepo.delete(cat);
	}

	@Override
	public CategoryDto GetCategory( Integer CategoryId) {
		Category Cat = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",CategoryId));
		return this.modalmapper.map(Cat, CategoryDto.class );
	}

	@Override
	public List<CategoryDto> getcategories() {
		 
	List<Category>categories =this.categoryrepo.findAll();
	List<CategoryDto> catdos = categories.stream().map((Cat)-> this.modalmapper.map(Cat,CategoryDto.class)).collect(Collectors.toList());
		return catdos;
	}
	
		
	@Override
	public List<CategoryDto> searchcategory(String Keyword) {
		List<Category> categories =	this.categoryrepo.findbyCategorynameContaining("%"+Keyword+"%");
		List<CategoryDto> categorydtos = categories.stream().map((product)-> this.modalmapper.map(product, CategoryDto.class)).collect(Collectors.toList());
			return categorydtos;
	}

	@Override
	public List<CategoryDto> getFurnitureCategories() {
		List<Category> categories = this.categoryrepo.findAll();
	    
	    List<CategoryDto> furnitureCategories = categories.stream()
	            .filter(category -> "Furniture".equalsIgnoreCase(category.getCategorytype()))
	            .map(cat -> this.modalmapper.map(cat, CategoryDto.class))
	            .collect(Collectors.toList());

	    return furnitureCategories;
		
	}

	@Override
	public List<CategoryDto> getDecorItems() {
		List<Category> categories = this.categoryrepo.findAll();
	    
	    List<CategoryDto> DecorItemsCategories = categories.stream()
	            .filter(category -> "Decor Items".equalsIgnoreCase(category.getCategorytype()))
	            .map(cat -> this.modalmapper.map(cat, CategoryDto.class))
	            .collect(Collectors.toList());
	    return DecorItemsCategories;
	}

}
