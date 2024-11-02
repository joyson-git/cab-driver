package com.cab_booking.cab_booking.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;

public interface DriverRepostary extends JpaRepository<Driver, Integer> {

	public  Driver findByEmail(String email);
	
	@Query("Select R from Ride R where R.status=REQUESTED AND R.driver.id=driverid")
	public List<Ride> getAllocatRides(@Param("driverId") Integer driverId);
	
	@Query("Select R from Ride R where R.status=COMPLETED AND R.driver.id=driverid")
	public List<Ride> getCompleteRides(@Param("driverId") Integer driverId);
	
	
	
}
