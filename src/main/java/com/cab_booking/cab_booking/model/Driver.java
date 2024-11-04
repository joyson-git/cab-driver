package com.cab_booking.cab_booking.model;

import java.util.List;

import com.cab_booking.cab_booking.model.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String email;
	private String mobile;
	private double rating;
	private double latitude;
	private double longitude;
	
private UserRole role;

private String password;


@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
private License license;

	@JsonIgnore
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL,orphanRemoval = true )
	private List<Ride> rides;
	
	
	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL,orphanRemoval = true )
	private Vehicle vehicle;
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL )
	private Ride currentRide ;
	
	private Integer totalRevenueInteger =0 ;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Ride getCurrentRide() {
		return currentRide;
	}

	public void setCurrentRide(Ride currentRide) {
		this.currentRide = currentRide;
	}

	public Integer getTotalRevenueInteger() {
		return totalRevenueInteger;
	}

	public void setTotalRevenueInteger(Integer totalRevenueInteger) {
		this.totalRevenueInteger = totalRevenueInteger;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", rating=" + rating
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", role=" + role + ", password=" + password
				+ ", license=" + license + ", rides=" + rides + ", vehicle=" + vehicle + ", currentRide=" + currentRide
				+ ", totalRevenueInteger=" + totalRevenueInteger + "]";
	}

	public Driver(Integer id, String name, String email, String mobile, double rating, double latitude,
			double longitude, UserRole role, String password, License license, List<Ride> rides, Vehicle vehicle,
			Ride currentRide, Integer totalRevenueInteger) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.rating = rating;
		this.latitude = latitude;
		this.longitude = longitude;
		this.role = role;
		this.password = password;
		this.license = license;
		this.rides = rides;
		this.vehicle = vehicle;
		this.currentRide = currentRide;
		this.totalRevenueInteger = totalRevenueInteger;
	}

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}


}
