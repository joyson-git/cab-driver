package com.cab_booking.cab_booking.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab_booking.cab_booking.model.Ride;

public interface RideRepository  extends JpaRepository<Ride, Integer>{

}
