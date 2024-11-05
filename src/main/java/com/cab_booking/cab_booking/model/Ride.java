package com.cab_booking.cab_booking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cab_booking.cab_booking.model.domain.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver;
	
	
	
	
@JsonIgnore
private List<Integer> declinedDrivers = new ArrayList<>();


private double pickupLatitude;

private double pickupLongitude;

private double destinationLatitude;
private double destinationLongitude;


private String pickupArea;

private String destinationArea;

private double distance;

private RideStatus status;

private long duration;

private LocalDateTime startTime;

private LocalDateTime endTime;

private double fare;

private int otp;

@Embedded
private PaymentDetails paymentDetails = new PaymentDetails();

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Driver getDriver() {
	return driver;
}

public void setDriver(Driver driver) {
	this.driver = driver;
}

public List<Integer> getDeclinedDrivers() {
	return declinedDrivers;
}

public void setDeclinedDrivers(List<Integer> declinedDrivers) {
	this.declinedDrivers = declinedDrivers;
}

public double getPickupLatitude() {
	return pickupLatitude;
}

public void setPickupLatitude(double pickupLatitude) {
	this.pickupLatitude = pickupLatitude;
}

public double getPickupLongitude() {
	return pickupLongitude;
}

public void setPickupLongitude(double pickupLongitude) {
	this.pickupLongitude = pickupLongitude;
}

public double getDestinationLatitude() {
	return destinationLatitude;
}

public void setDestinationLatitude(double destinationLatitude) {
	this.destinationLatitude = destinationLatitude;
}

public double getDestinationLongitude() {
	return destinationLongitude;
}

public void setDestinationLongitude(double destinationLongitude) {
	this.destinationLongitude = destinationLongitude;
}

public String getPickupArea() {
	return pickupArea;
}

public void setPickupArea(String pickupArea) {
	this.pickupArea = pickupArea;
}

public String getDestinationArea() {
	return destinationArea;
}

public void setDestinationArea(String destinationArea) {
	this.destinationArea = destinationArea;
}

public double getDistance() {
	return distance;
}

public void setDistance(double distance) {
	this.distance = distance;
}

public RideStatus getStatus() {
	return status;
}

public void setStatus(RideStatus status) {
	this.status = status;
}

public long getDuration() {
	return duration;
}

public void setDuration(long duration) {
	this.duration = duration;
}

public LocalDateTime getStartTime() {
	return startTime;
}

public void setStartTime(LocalDateTime startTime) {
	this.startTime = startTime;
}

public LocalDateTime getEndTime() {
	return endTime;
}

public void setEndTime(LocalDateTime endTime) {
	this.endTime = endTime;
}

public double getFare() {
	return fare;
}

public void setFare(double fare) {
	this.fare = fare;
}

public int getOtp() {
	return otp;
}

public void setOtp(int otp) {
	this.otp = otp;
}

public PaymentDetails getPaymentDetails() {
	return paymentDetails;
}

public void setPaymentDetails(PaymentDetails paymentDetails) {
	this.paymentDetails = paymentDetails;
}

public Ride(Integer id, User user, Driver driver, List<Integer> declinedDrivers, double pickupLatitude,
		double pickupLongitude, double destinationLatitude, double destinationLongitude, String pickupArea,
		String destinationArea, double distance, RideStatus status, long duration, LocalDateTime startTime,
		LocalDateTime endTime, double fare, int otp, PaymentDetails paymentDetails) {
	super();
	this.id = id;
	this.user = user;
	this.driver = driver;
	this.declinedDrivers = declinedDrivers;
	this.pickupLatitude = pickupLatitude;
	this.pickupLongitude = pickupLongitude;
	this.destinationLatitude = destinationLatitude;
	this.destinationLongitude = destinationLongitude;
	this.pickupArea = pickupArea;
	this.destinationArea = destinationArea;
	this.distance = distance;
	this.status = status;
	this.duration = duration;
	this.startTime = startTime;
	this.endTime = endTime;
	this.fare = fare;
	this.otp = otp;
	this.paymentDetails = paymentDetails;
}

public Ride() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Ride [id=" + id + ", user=" + user + ", driver=" + driver + ", declinedDrivers=" + declinedDrivers
			+ ", pickupLatitude=" + pickupLatitude + ", pickupLongitude=" + pickupLongitude + ", destinationLatitude="
			+ destinationLatitude + ", destinationLongitude=" + destinationLongitude + ", pickupArea=" + pickupArea
			+ ", destinationArea=" + destinationArea + ", distance=" + distance + ", status=" + status + ", duration="
			+ duration + ", startTime=" + startTime + ", endTime=" + endTime + ", fare=" + fare + ", otp=" + otp
			+ ", paymentDetails=" + paymentDetails + "]";
}


}
