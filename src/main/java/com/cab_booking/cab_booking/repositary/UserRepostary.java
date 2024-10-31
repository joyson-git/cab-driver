package com.cab_booking.cab_booking.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab_booking.cab_booking.model.User;

public interface UserRepostary extends JpaRepository<User, Integer> {

	
	public  User findByEmail(String email);
}
