package com.cab_booking.cab_booking.request;

import com.cab_booking.cab_booking.model.License;
import com.cab_booking.cab_booking.model.Vehicle;

import jakarta.persistence.OneToOne;

public class DriverSigupRequest {

	private String name;
	private String email;
	private String mobile;
	private String password;
	private double latitude;
	private double longitude;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "DriverSigupRequest [name=" + name + ", email=" + email + ", mobile=" + mobile + ", password=" + password
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	public DriverSigupRequest(String name, String email, String mobile, String password, double latitude,
			double longitude) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
//	@OneToOne(mappedBy = "driver")
//	private License license;
	
//	@OneToOne(mappedBy = "driver")
//	private Vehicle vehicle;

	
	
}
