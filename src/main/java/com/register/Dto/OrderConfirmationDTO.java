package com.register.Dto;

import java.time.LocalDateTime;
import java.util.List;



public class OrderConfirmationDTO {
    private Integer id;
    private Integer userId;
    private double totalAmount;
    private LocalDateTime confirmationDate;
    private String modeOfPayment;
    private String address;
    private String Username;
    private List<OrderConfirmationItemDTO> confirmationItems;
    
    
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderConfirmationItemDTO> getConfirmationItems() {
        return confirmationItems;
    }

    public void setConfirmationItems(List<OrderConfirmationItemDTO> confirmationItems) {
        this.confirmationItems = confirmationItems;
    }

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}
    
}
