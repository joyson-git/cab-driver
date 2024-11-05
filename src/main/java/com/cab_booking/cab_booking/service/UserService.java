package com.cab_booking.cab_booking.service;


import java.util.List;

import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;

public interface UserService {
	
	 public User createUser(User user) throws UserException;
	    public User getReqUserProfile(String token) throws UserException;
	    public User findUserById(Integer id) throws UserException;
	    public List<Ride> completedRides(Integer userId) throws UserException;
}
