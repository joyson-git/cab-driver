package com.cab_booking.cab_booking.dtos;

import java.time.LocalDateTime;

import com.cab_booking.cab_booking.model.PaymentDetails;
import com.cab_booking.cab_booking.model.domain.RideStatus;

public class RideDTO {

	private Integer id;
	private UserDTO user;
	private DriverDTO driver;
	private double pickupLatitude;
	private double pickupLongitude;
	private double destinationLatitude;
	private double destinationLongitude;
	private String pickupArea;
	private String destinationArea;
	private double distance;
	private long duration;
	private RideStatus status;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double fare;
	private PaymentDetails paymentDetails;
	private int otp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public DriverDTO getDriver() {
		return driver;
	}
	public void setDriver(DriverDTO driver) {
		this.driver = driver;
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
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public RideStatus getStatus() {
		return status;
	}
	public void setStatus(RideStatus status) {
		this.status = status;
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
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "RideDTO [id=" + id + ", user=" + user + ", driver=" + driver + ", pickupLatitude=" + pickupLatitude
				+ ", pickupLongitude=" + pickupLongitude + ", destinationLatitude=" + destinationLatitude
				+ ", destinationLongitude=" + destinationLongitude + ", pickupArea=" + pickupArea + ", destinationArea="
				+ destinationArea + ", distance=" + distance + ", duration=" + duration + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", fare=" + fare + ", paymentDetails="
				+ paymentDetails + ", otp=" + otp + "]";
	}
	public RideDTO(Integer id, UserDTO user, DriverDTO driver, double pickupLatitude, double pickupLongitude,
			double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea,
			double distance, long duration, RideStatus status, LocalDateTime startTime, LocalDateTime endTime,
			double fare, PaymentDetails paymentDetails, int otp) {
		super();
		this.id = id;
		this.user = user;
		this.driver = driver;
		this.pickupLatitude = pickupLatitude;
		this.pickupLongitude = pickupLongitude;
		this.destinationLatitude = destinationLatitude;
		this.destinationLongitude = destinationLongitude;
		this.pickupArea = pickupArea;
		this.destinationArea = destinationArea;
		this.distance = distance;
		this.duration = duration;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fare = fare;
		this.paymentDetails = paymentDetails;
		this.otp = otp;
	}
	public RideDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
