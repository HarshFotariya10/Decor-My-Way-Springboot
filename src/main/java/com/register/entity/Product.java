	package com.register.entity;
	
	
	
	import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
	
	@jakarta.persistence.Entity
	@Table(name ="product")
	public class Product {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int productId;
		
		private String productname;
		
		@Column(length = 10000)
		private String Description;
		private String image;
		private long quantity;
		private long price;
		
		
		
		@ManyToOne
		@JoinColumn(name="categoryID")
		private Category category;
	
	    @OneToMany(mappedBy = "product",cascade = jakarta.persistence.CascadeType.ALL)
	    private List<CartItem> cartItems;
		
	    @OneToMany(mappedBy = "product", cascade = jakarta.persistence.CascadeType.ALL)
	    private List<OrderConfirmationItem> OrderConfirmation;
	
		
		
	
	
		public Product(int productId, String productname, String description, String image, long quantity, long price,
				Category category) {
			super();
			this.productId = productId;
			this.productname = productname;
			Description = description;
			this.image = image;
			this.quantity = quantity;
			this.price = price;
			this.category = category;
		}
	
	
		public Product() {
			super();
		}
	
	
		public int getProductId() {
			return productId;
		}
	
		public void setProductId(int productId) {
			this.productId = productId;
		}
	
		public String getProductname() {
			return productname;
		}
	
		public void setProductname(String productname) {
			this.productname = productname;
		}
	
		
	
		public Category getCategory() {
			return category;
		}
	
		public void setCategory(Category category) {
			this.category = category;
		}
	
	
		public String getDescription() {
			return Description;
		}
	
	
		public void setDescription(String description) {
			Description = description;
		}
	
	
		public long getQuantity() {
			return quantity;
		}
	
	
		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}
	
	
		public long getPrice() {
			return price;
		}
	
	
		public void setPrice(long price) {
			this.price = price;
		}
	
	
		public String getImage() {
			return image;
		}
	
	
		public void setImage(String image) {
			this.image = image;
		}
		
		
		
		
		
		
	
	}
