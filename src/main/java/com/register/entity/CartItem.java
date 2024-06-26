	package com.register.entity;
	
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;
	
	@jakarta.persistence.Entity
	@Table(name = "cart_item")
	public class CartItem {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartItemId;
	
	    @ManyToOne
	    @JoinColumn(name = "cart_id")
	    private Cart cart;
	
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	
	    private int quantity;
	    
	 
		public CartItem(Long cartItemId, Cart cart, Product product, int quantity) {
			super();
			this.cartItemId = cartItemId;
			this.cart = cart;
			this.product = product;
			this.quantity = quantity;
			
		}
		public CartItem() {
			super();
		}
		public Long getCartItemId() {
			return cartItemId;
		}
		public void setCartItemId(Long cartItemId) {
			this.cartItemId = cartItemId;
		}
		public Cart getCart() {
			return cart;
		}
		public void setCart(Cart cart) {
			this.cart = cart;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	
		
		
	    
	    
	    
	}
