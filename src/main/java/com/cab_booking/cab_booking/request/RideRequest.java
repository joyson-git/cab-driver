package com.cab_booking.cab_booking.request;

public class RideRequest {
	
	private double pickupLongitude;
	private double pickupLatitude;
	private double destinationLongitude;
	private double destinationLatitude;
	private String pickupArea;
	private String destinationArea;
	
	public double getPickupLongitude() {
		return pickupLongitude;
	}
	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}
	public double getPickupLatitude() {
		return pickupLatitude;
	}
	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}
	public double getDestinationLongitude() {
		return destinationLongitude;
	}
	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}
	public double getDestinationLatitude() {
		return destinationLatitude;
	}
	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
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
	public RideRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RideRequest(double pickupLongitude, double pickupLatitude, double destinationLongitude,
			double destinationLatitude, String pickupArea, String destinationArea) {
		super();
		this.pickupLongitude = pickupLongitude;
		this.pickupLatitude = pickupLatitude;
		this.destinationLongitude = destinationLongitude;
		this.destinationLatitude = destinationLatitude;
		this.pickupArea = pickupArea;
		this.destinationArea = destinationArea;
	}
	@Override
	public String toString() {
		return "RideRequest [pickupLongitude=" + pickupLongitude + ", pickupLatitude=" + pickupLatitude
				+ ", destinationLongitude=" + destinationLongitude + ", destinationLatitude=" + destinationLatitude
				+ ", pickupArea=" + pickupArea + ", destinationArea=" + destinationArea + "]";
	}
}
