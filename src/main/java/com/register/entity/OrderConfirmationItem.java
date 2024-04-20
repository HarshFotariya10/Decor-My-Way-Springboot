package com.register.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderConfirmationItem {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "order_confirmation_id", referencedColumnName = "id")
	    private OrderConfirmation orderConfirmation;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
		private int quantity;

	    public OrderConfirmationItem(Long id, OrderConfirmation orderConfirmation, Product product, int quantity) {
			super();
			this.id = id;
			this.orderConfirmation = orderConfirmation;
			this.product = product;
			this.quantity = quantity;
		}
	    
	    

		public OrderConfirmationItem() {
			super();
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public OrderConfirmation getOrderConfirmation() {
			return orderConfirmation;
		}

		public void setOrderConfirmation(OrderConfirmation orderConfirmation) {
			this.orderConfirmation = orderConfirmation;
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
