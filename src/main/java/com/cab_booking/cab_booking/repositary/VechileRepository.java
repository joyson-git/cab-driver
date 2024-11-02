package com.cab_booking.cab_booking.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab_booking.cab_booking.model.Vehicle;

public interface VechileRepository  extends JpaRepository<Vehicle, Integer>{

}
