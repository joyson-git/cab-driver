package com.cab_booking.cab_booking.request;

public class StartRideRequest {

	
	private Integer otp;

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public StartRideRequest(Integer otp) {
		super();
		this.otp = otp;
	}

	public StartRideRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
}
