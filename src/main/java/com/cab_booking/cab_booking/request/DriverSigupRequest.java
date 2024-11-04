package com.cab_booking.cab_booking.request;

import com.cab_booking.cab_booking.model.License;
import com.cab_booking.cab_booking.model.Vehicle;

public class DriverSigupRequest {

	private String name;
	private String email;
	private String mobile;
	private String password;
	private double latitude;
	private double longitude;
	 private License license;  // Assuming this is the field for license
	 private Vehicle vehicle;
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
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	@Override
	public String toString() {
		return "DriverSigupRequest [name=" + name + ", email=" + email + ", mobile=" + mobile + ", password=" + password
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", license=" + license + ", vehicle="
				+ vehicle + "]";
	}
	public DriverSigupRequest(String name, String email, String mobile, String password, double latitude,
			double longitude, License license, Vehicle vehicle) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.license = license;
		this.vehicle = vehicle;
	}
	public DriverSigupRequest() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
