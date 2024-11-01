package com.cab_booking.cab_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.UserRepostary;


@Service
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
		
		User user = userRepostary.findByEmail(username);
		
		if(user!=null) {
			return new org.springframework.security.core.userdetails
					.User(user.getEmail(),user.getPassword(), authorities);
		}
		Driver driver = driverRepostary.findByEmail(username);
		if(driver!=null) {
			return new org.springframework.security.core.userdetails
					.User(driver.getEmail(),driver.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("user name not found");
	}

}
