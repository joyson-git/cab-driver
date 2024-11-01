package com.cab_booking.cab_booking.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {

	
	@NotBlank(message = "Email is Required")
	@Email(message = "Email should be valid")
	private String email;
	
	private String fullName;
	
	private String password;
	
	private String mobile;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public SignupRequest(
			@NotBlank(message = "Email is Required") @Email(message = "Email should be valid") String email,
			String fullName, String password, String mobile) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", fullName=" + fullName + ", password=" + password + ", mobile="
				+ mobile + "]";
	}

	
	
	
	
	
	
}
