package com.cab_booking.cab_booking.dtos;

public class LicenseDTO {
	private String number;
	private String expiryDate;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public LicenseDTO(String number, String expiryDate) {
		super();
		this.number = number;
		this.expiryDate = expiryDate;
	}
	public LicenseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	}

