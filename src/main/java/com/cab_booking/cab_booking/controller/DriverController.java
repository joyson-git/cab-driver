package com.cab_booking.cab_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.service.driverService;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    private driverService driverService;

    @GetMapping("/profile")
    public ResponseEntity<Driver> getReqDriverProfileHandler(@RequestHeader("Authorization") String jwt) throws DriverException {
        Driver driver = driverService.getRequestedDriver(jwt);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @GetMapping("/{driverId}/current_ride")
    public ResponseEntity<Ride> getDriversCurrentRideHandler(@PathVariable Integer driverId) throws DriverException {
        Ride ride = driverService.getDriverCurrentRide(driverId);
        return new ResponseEntity<>(ride, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{driverId}/allocated")
    public ResponseEntity<List<Ride>> getAllocatedRideHandler(@PathVariable Integer driverId) throws DriverException {
        List<Ride> rides = driverService.getAllocatedRides(driverId);
        return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rides/completed")
    public ResponseEntity<List<Ride>> getCompleteRidesHandler(@RequestHeader("Authorization") String jwt) throws DriverException {
        Driver driver = driverService.getRequestedDriver(jwt);
        List<Ride> rides = driverService.getCompletedRides(driver.getId());
        return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
    }
}
