package com.cab_booking.cab_booking.service;

import java.util.List;

import org.apache.el.stream.Optional;
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
	public User createUser(User user) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getReqUserProfile(String token) throws UserException {
		String email = jwtUtil.getEmailFromJwt(token);
		User user = userRepository.findByEmail(email);
		if(user!=null) {
		return user;
		}
		throw new UserException("invalid token...");
		}
	

	@Override
	public User findUserById(Integer Id) throws UserException {
		Optional<User> opt=userRepository.findById(Id);
		if(opt.isPresent()) {
		return opt.get();
		}
		throw new UserException("user not found with id "+Id);
		}
	
/*
	@Override
	public User findUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByToken(String token) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<Ride> completedRids(Integer userld) throws UserException {
		List <Ride> completedRides =userRepository.getCompletedRides(userId);
		return completedRides;
		
	}
	

}
