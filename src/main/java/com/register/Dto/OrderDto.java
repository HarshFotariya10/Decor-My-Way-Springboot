package com.register.Dto;

import com.register.entity.Cart;
import com.register.entity.Entity;

public class OrderDto {
	private Long orderId;
    private long user;
    
	
	    


		public OrderDto(Long orderId, Long user) {
			super();
			this.orderId = orderId;
			this.user = user;
			
		}

		public OrderDto() {
			super();
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Long getUser() {
			return user;
		}

		public void setUser(Long user) {
			this.user = user;
		}

		
		




		
		
	    
}
