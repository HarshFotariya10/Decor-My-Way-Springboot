package com.register.entity;






import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Entity entity;

    @OneToMany(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItems;

	public Order(Long orderId, Entity entity, List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.entity = entity;
		this.orderItems = orderItems;
	}
	 public Order() {
	        this.orderItems = new ArrayList<>();  // Initialize the orderItems list in the constructor
	    }

	





	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
    
    
}
