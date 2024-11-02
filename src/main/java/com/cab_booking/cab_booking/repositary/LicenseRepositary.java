package com.cab_booking.cab_booking.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab_booking.cab_booking.model.License;

public interface LicenseRepositary  extends JpaRepository<License, Integer>{

}
