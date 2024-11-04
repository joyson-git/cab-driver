package com.cab_booking.cab_booking.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cab_booking.cab_booking.model.Ride;

public interface RideRepository  extends JpaRepository<Ride, Integer>{

	
	 @Query("SELECT r FROM Ride r WHERE r.driver.id = :driverId AND r.status <> 'COMPLETED'")
	    List<Ride> getAllocatedRides(Integer driverId);
	 
	
	 @Query("SELECT r FROM Ride r WHERE r.driver.id = :driverId AND r.status = 'COMPLETED'")
	    List<Ride> getCompletedRides(Integer driverId);
	 
	 
}
