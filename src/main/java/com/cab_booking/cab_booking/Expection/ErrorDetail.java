package com.cab_booking.cab_booking.Expection;

import java.time.LocalDateTime;

public class ErrorDetail {

	
	private String error;
	private String details;
	private LocalDateTime timestamp;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ErrorDetail [error=" + error + ", details=" + details + ", timestamp=" + timestamp + "]";
	}
	public ErrorDetail(String error, String details, LocalDateTime timestamp) {
		super();
		this.error = error;
		this.details = details;
		this.timestamp = timestamp;
	}
	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
