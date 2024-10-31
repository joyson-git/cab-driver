package com.cab_booking.cab_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.UserRepostary;

public class CustomerUserDetails implements  UserDetailsService{

	private DriverRepostary driverRepostary;
	private UserRepostary userRepostary;
	
	 @Autowired
	    public CustomerUserDetails(DriverRepostary driverRepostary, UserRepostary userRepostary) {
	        this.driverRepostary = driverRepostary;
	        this.userRepostary = userRepostary;
	    }
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		return null;
	}

}
