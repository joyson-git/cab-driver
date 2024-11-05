package com.cab_booking.cab_booking.dtos;

import com.cab_booking.cab_booking.model.Vehicle;
import com.cab_booking.cab_booking.model.domain.UserRole;

public class DriverDTO {

	
	private Integer id;
	private String name;
	private String email;
	private String mobile;
	private double rating;
	private double latitude;
	private double longitude;
	private UserRole role;
	private Vehicle vehicle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
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
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public DriverDTO(Integer id, String name, String email, String mobile, double rating, double latitude,
			double longitude, UserRole role, Vehicle vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.rating = rating;
		this.latitude = latitude;
		this.longitude = longitude;
		this.role = role;
		this.vehicle = vehicle;
	}
	public DriverDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DriverDTO [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", rating="
				+ rating + ", latitude=" + latitude + ", longitude=" + longitude + ", role=" + role + ", vehicle="
				+ vehicle + "]";
	}
	
}
