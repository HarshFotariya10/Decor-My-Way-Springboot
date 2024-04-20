package com.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "order_item")
public class OrderItem {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long orderItemId;

	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    private Order order;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    @JsonIgnore
	    private Product product;

	    private int quantity;

		public OrderItem(Long orderItemId, Order order, Product product, int quantity) {
			super();
			this.orderItemId = orderItemId;
			this.order = order;
			this.product = product;
			this.quantity = quantity;
		}

		public OrderItem() {
			super();
		}

		public Long getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(Long orderItemId) {
			this.orderItemId = orderItemId;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
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
