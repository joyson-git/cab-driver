package com.cab_booking.cab_booking.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab_booking.cab_booking.model.Driver;

public interface DriverRepostary extends JpaRepository<Driver, Integer> {

	public  Driver findByEmail(String email);
}
