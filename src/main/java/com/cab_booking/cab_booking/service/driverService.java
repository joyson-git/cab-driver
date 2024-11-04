package com.cab_booking.cab_booking.service;

import java.util.List;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.request.DriverSigupRequest;


public interface driverService {
    
	
	//public  Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude, Ride ride);
    // Registers a new driver with the details provided in the DriverSignupRequest
    public Driver registerDriver(DriverSigupRequest driverSignupRequest);
    
    // Retrieves a list of available drivers within a certain radius of the pickup location
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride);
    
    // Finds the nearest driver from the list of available drivers
    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double radius, Ride ride);
    
    // Retrieves the requested driver by decoding the JWT
    public Driver getRequestedDriver(String jwt) throws DriverException;
    
    // Gets the current ride of the driver based on their ID
    public Ride getDriverCurrentRide(Integer driverId) throws DriverException;
    
    // Retrieves a list of rides allocated to a particular driver by their ID
    public List<Ride> getAllocatedRides(Integer driverId) throws DriverException;
    
    // Finds a driver by their ID
    public Driver findDriverById(Integer driverId) throws DriverException;
    
    // Retrieves a list of completed rides for a specific driver by their ID
    public List<Ride> getCompletedRides(Integer driverId) throws DriverException;
}
