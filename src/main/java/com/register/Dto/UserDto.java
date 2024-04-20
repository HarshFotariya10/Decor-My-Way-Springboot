package com.register.Dto;

public class UserDto {
	private long id;
	private String name;
	private long moblieno;
	private String email;
	private String address;
	private int pincode;
	public UserDto(String name, long moblieno, String email, String address, int pincode) {
		super();
		this.name = name;
		this.moblieno = moblieno;
		this.email = email;
		this.address = address;
		this.pincode = pincode;
	}
	public UserDto() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
