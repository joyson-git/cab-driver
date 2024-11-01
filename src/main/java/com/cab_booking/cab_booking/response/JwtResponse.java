package com.cab_booking.cab_booking.response;

import com.cab_booking.cab_booking.model.domain.UserRole;

public class JwtResponse {

	
	private String jwt;
	private String message;
	private boolean isAuthenticated;
	private boolean isError;
	private String errorDetails;
	private UserRole type;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public UserRole getType() {
		return type;
	}
	public void setType(UserRole type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "JwtResponse [jwt=" + jwt + ", message=" + message + ", isAuthenticated=" + isAuthenticated
				+ ", isError=" + isError + ", errorDetails=" + errorDetails + ", type=" + type + "]";
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String jwt, String message, boolean isAuthenticated, boolean isError, String errorDetails,
			UserRole type) {
		super();
		this.jwt = jwt;
		this.message = message;
		this.isAuthenticated = isAuthenticated;
		this.isError = isError;
		this.errorDetails = errorDetails;
		this.type = type;
	}
	
	
	
}
