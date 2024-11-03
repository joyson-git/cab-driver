package com.cab_booking.cab_booking.service;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.Expection.RideException;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.request.RideRequest;

public interface RiderService {

    public Ride requestRide(RideRequest rideRequest, User user) throws DriverException;

    public Ride createRideRequest(User user, Driver nearestDriver,
                                  double pickupLatitude,
                                  double pickupLongitude,
                                  double destinationLatitude,
                                  double destinationLongitude,
                                  String pickupArea,
                                  String destinationArea) throws DriverException;

    public void acceptRide(Integer rideId) throws RideException;

    public void declineRide(Integer rideId, Integer driverId) throws RideException;

    public void startRide(Integer rideId, int option) throws RideException;

    public void completeRide(Integer rideId) throws RideException;

    public void cancelRide(Integer rideId) throws RideException;

    public Ride findRideById(Integer rideId) throws RideException;
}
