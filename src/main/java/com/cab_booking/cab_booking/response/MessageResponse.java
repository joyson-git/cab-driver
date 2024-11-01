package com.cab_booking.cab_booking.response;

public class MessageResponse {
	
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageResponse [message=" + message + "]";
	}

	public MessageResponse(String message) {
		super();
		this.message = message;
	}
	

}
