package com.cab_booking.cab_booking.service;

import java.util.List;

import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.configuration.JwtUtil;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.repositary.UserRepostary;



@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	private UserRepostary userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	


	
	@Override
    public User getReqUserProfile(String token) throws UserException {
        String email = jwtUtil.getEmailFromJwt(token);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new UserException("Invalid token...");
    }

	@Override
    public User createUser(User user) throws UserException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserException("User already exists with this email.");
        }
        return userRepository.save(user);
    }

	  @Override
	    public User findUserById(Integer id) throws UserException {
	        Optional <User> opt = userRepository.findById(id);
	        if (opt.isPresent()) {
	            return opt.get();
	        }
	        throw new UserException("User not found with id " + id);
	    }
	
	
/*
	@Override
	public User findUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
*/


	


	
	@Override
    public List<Ride> completedRides(Integer userId) throws UserException {
        List<Ride> completedRides = userRepository.getCompletedRide(userId);
        if (completedRides.isEmpty()) {
            throw new UserException("No completed rides found for user with id " + userId);
        }
        return completedRides;
    }
}
