package com.cab_booking.cab_booking.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;

public interface UserRepostary extends JpaRepository<User, Integer> {

	
	public  User findByEmail(String email);
	
	@Query("Select R From Rider where r.status= COMPLETED AND r.user.id=:userld")
	public List <Ride> getCompletedRide(@Param("userid") Integer userId);
}
