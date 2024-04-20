package com.register.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OrderConfirmation {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

		 @Column(name = "user_id") // Add user id column
		 private Integer userId;
		 

	    @Column(name = "total_amount")
	    private double totalAmount;

	    @Column(name = "confirmation_date")
	    private LocalDateTime confirmationDate;

	

	    @Column(name = "mode_of_payment")
	    private String modeOfPayment;
	    
	    @Column(name = "Address")
	    private String address;
	    
	    
	    @OneToMany(mappedBy = "orderConfirmation",  cascade = jakarta.persistence.CascadeType.ALL)
	    private List<OrderConfirmationItem> confirmationItems = new ArrayList<>();


	    

	    public OrderConfirmation() {
	        // Default constructor
	    }



	    public OrderConfirmation(Integer id, Integer userId,  double totalAmount,
				LocalDateTime confirmationDate,  String modeOfPayment) {
			super();
			this.id = id;
			this.userId = userId;

			this.totalAmount = totalAmount;
			this.confirmationDate = confirmationDate;
			this.modeOfPayment = modeOfPayment;
		}



		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }



	    public Integer getUserId() {
			return userId;
		}



		public void setUserId(Integer userId) {
			this.userId = userId;
		}


	    public double getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(double totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public LocalDateTime getConfirmationDate() {
	        return confirmationDate;
	    }

	    public void setConfirmationDate(LocalDateTime confirmationDate) {
	        this.confirmationDate = confirmationDate;
	    }



	    public String getModeOfPayment() {
	        return modeOfPayment;
	    }

	    public void setModeOfPayment(String modeOfPayment) {
	        this.modeOfPayment = modeOfPayment;
	    }



		public List<OrderConfirmationItem> getConfirmationItems() {
			return confirmationItems;
		}



		public void setConfirmationItems(List<OrderConfirmationItem> confirmationItems) {
			this.confirmationItems = confirmationItems;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}
	    
	
}
