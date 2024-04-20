package com.register.Serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.register.Dto.CartDto;
import com.register.Dto.CartItemDto;
import com.register.Exceptions.ResourceNotFoundException;
import com.register.entity.Cart;
import com.register.entity.CartItem;
import com.register.entity.Entity;
import com.register.entity.Product;
import com.register.rep.CartItemRepo;
import com.register.rep.CartRepo;
import com.register.rep.ProductRepo;
import com.register.rep.Repo;
import com.register.service.CartService;

@Service
public class CartImpl implements CartService {
    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private Repo userRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CartItemRepo cartItemRepository;
    
	@Value("${product_file_path}")
	private String filepPath;

	@Override
	public void addToCart(Integer userId, Integer productId) {
	    // Set default quantity to 1
	    int defaultQuantity = 1;

	    Entity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

	    Cart cart = user.getCart();
	    if (cart == null) {
	        cart = new Cart();
	        cart.setEntity(user);
	        cartRepository.save(cart);
	    }

	    // Retrieve product and check if it exists
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", productId));

	    // Check if the product is already in the cart
	    Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
	    long availableQuantity = product.getQuantity();
	    long totalQuantityInCart = existingCartItem.map(CartItem::getQuantity).orElse(0);

	    if (availableQuantity - totalQuantityInCart >= defaultQuantity) {
		    if (existingCartItem.isPresent()) {
		        // Update quantity if the product is already in the cart
		        CartItem cartItem = existingCartItem.get();
		        cartItem.setQuantity(cartItem.getQuantity() + defaultQuantity);
		        cartItemRepository.save(cartItem);
		    } else {
		        // Add a new cart item if the product is not in the cart
		        CartItem cartItem = new CartItem();
		        cartItem.setCart(cart);
		        cartItem.setProduct(product);
		        cartItem.setQuantity(defaultQuantity);
		        cartItemRepository.save(cartItem);
		    }
	    }
	    else {
	    	throw new IllegalArgumentException("Product quantity not available");
	    }
	}
	
	

	public List<CartDto> viewCart(Integer userId) {
	    Entity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    Cart cart = user.getCart();

	    List<CartDto> cartDTOs = new ArrayList<>();

	    if (cart != null) {
	        // Map cart items to DTOs
	        List<CartItemDto> cartItemDTOs = cart.getCartItems().stream()
	                .map(cartItem -> {
	                    CartItemDto cartItemDTO = new CartItemDto();
	                    
	                    cartItemDTO.setProductId(cartItem.getProduct().getProductId());
	                    cartItemDTO.setProductname(cartItem.getProduct().getProductname());
	                    cartItemDTO.setImagename(filepPath + cartItem.getProduct().getImage());
	                    cartItemDTO.setQuantity(cartItem.getQuantity());
	                    cartItemDTO.setPrice(cartItem.getProduct().getPrice()); // Assuming there's a getPrice() method in Product entity
	                    return cartItemDTO;
	                })
	                .collect(Collectors.toList());

	        // Calculate total amount for each cart
	        double totalAmount = cartItemDTOs.stream()
	                .mapToDouble(cartItemDTO -> cartItemDTO.getPrice() * cartItemDTO.getQuantity())
	                .sum();

	        // Create a CartDto for each cart
	        CartDto cartDTO = new CartDto();
	        cartDTO.setCartId(cart.getCartId());
	        cartDTO.setUserId(userId);
	        cartDTO.setCartItems(cartItemDTOs);
	        cartDTO.setTotalamount(totalAmount);

	        cartDTOs.add(cartDTO);
	    }

	    return cartDTOs;
	}

//	@Override
//	public List<CartDto> viewCart(Integer userId) {
//		Entity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//	    Cart cart = user.getCart();
//
//	    CartDto cartDTO = new CartDto();
//	    cartDTO.setUserId(userId);
//
//	    if (cart != null) {
//	        // Map cart items to DTO
//	        List<CartItemDto> cartItemDTOs = cart.getCartItems().stream()
//	                .map(cartItem -> {
//	                    CartItemDto cartItemDTO = new CartItemDto();
//	                    cartItemDTO.setProductId(cartItem.getProduct().getProductId());
//	                    cartItemDTO.setProductname(cartItem.getProduct().getProductname());
//	                    cartItemDTO.setImagename(cartItem.getProduct().getImage());
//	                    cartItemDTO.setQuantity(cartItem.getQuantity());
//	                    cartItemDTO.setPrice(cartItem.getProduct().getPrice()); // Assuming there's a getPrice() method in Product entity
//	                    return cartItemDTO;
//	                })
//	                .collect(Collectors.toList());
//
//	        cartDTO.setCartItems(cartItemDTOs);
//
//	        // Calculate total amount
//	        double totalAmount = cartItemDTOs.stream()
//	                .mapToDouble(cartItemDTO -> cartItemDTO.getPrice() * cartItemDTO.getQuantity())
//	                .sum();
//
//	        cartDTO.setTotalamount(totalAmount);
//	    }
//
//	    return cartDTO;
//}


	@Override
	public void reduceQuantity(Integer userId, Integer productId) {
		int reduceBy = 1;

	    Entity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	    Cart cart = user.getCart();
	    if (cart != null) {
	        // Retrieve product and check if it exists
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        // Check if the product is already in the cart
	        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

	        if (existingCartItem.isPresent()) {
	            // Reduce quantity if the product is already in the cart
	            CartItem cartItem = existingCartItem.get();
	            int newQuantity = Math.max(cartItem.getQuantity() - reduceBy, 0); // Ensure quantity is not negative
	            cartItem.setQuantity(newQuantity);
	            if (newQuantity == 0) {
	                // Remove the item from the cart if quantity becomes zero
	                cartItemRepository.delete(cartItem);
	            } else {
	                cartItemRepository.save(cartItem);
	            }
	        }
	        // You may add an else branch here if you want to handle the case where the product is not in the cart.
	    }
	    
	}
	 public int countProductsInUserCart(Integer userId) {
	        // Find the user by ID
	        Entity user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        // Get the user's cart
	        Cart cart = user.getCart();
	        if (cart == null) {
	            // If the user has no cart, return 0
	            return 0;
	        }

	        // Calculate the total count of products in the cart
	        int totalCount = cart.getCartItems().size();
	        return totalCount;
	    }

}
