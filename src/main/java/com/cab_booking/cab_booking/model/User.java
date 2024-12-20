package com.cab_booking.cab_booking.model;

import com.cab_booking.cab_booking.model.domain.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
  private String email;
  
  private String  FullName;
  
  public User(String fullName) {
	super();
	FullName = fullName;
}

public String getFullName() {
	return FullName;
}

public void setFullName(String fullName) {
	FullName = fullName;
}

private String mobile;
	
  private String password;
  
  private String profilePicture;
  
  private UserRole role;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
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

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getProfilePicture() {
	return profilePicture;
}

public void setProfilePicture(String profilePicture) {
	this.profilePicture = profilePicture;
}

public UserRole getRole() {
	return role;
}

public void setRole(UserRole role) {
	this.role = role;
}

public User(Integer id, String email, String mobile, String password, String profilePicture, UserRole role) {
	super();
	this.id = id;
	this.email = email;
	this.mobile = mobile;
	this.password = password;
	this.profilePicture = profilePicture;
	this.role = role;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}
  
  
	
}
