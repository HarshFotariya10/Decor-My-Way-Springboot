package com.register.entity;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@jakarta.persistence.Entity

@Table(name = "registration")

public class Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "fullname")
	private String name;
	private long moblieno;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	private String address;
	private int pincode;
	private String date;
	private String password;
	
    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL)
    @JsonIgnore 
    private Cart cart;
    
    
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Order> orders;
    
	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public Entity(long id, String name, long moblieno, String email, String address, int pincode, String date,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.moblieno = moblieno;
		this.email = email;
		this.address = address;
		this.pincode = pincode;
		this.date = date;
		this.password = password;
	}


	public Entity() {
		super();
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public long getMoblieno() {
		return moblieno;
	}

	public void setMoblieno(long moblieno) {
		this.moblieno = moblieno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", moblieno=" + moblieno + ", email=" + email + ", address="
				+ address + ", pincode=" + pincode + ", date=" + date + ", password=" + password + "]";
	}


	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}

