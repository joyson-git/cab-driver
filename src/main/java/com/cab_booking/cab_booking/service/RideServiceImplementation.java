package com.cab_booking.cab_booking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.Expection.RideException;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.model.domain.RideStatus;
import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.RideRepository;
import com.cab_booking.cab_booking.request.RideRequest;

@Service
public class RideServiceImplementation implements RiderService {

    @Autowired
    private driverService driverService;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private Calculators calculators;

    @Autowired
    private DriverRepostary driverRepostary;

    @Override
    public Ride requestRide(RideRequest rideRequest, User user) throws DriverException {
        double pickupLatitude = rideRequest.getPickupLatitude();
        double pickupLongitude = rideRequest.getPickupLongitude();
        double destinationLatitude = rideRequest.getDestinationLatitude();
        double destinationLongitude = rideRequest.getDestinationLongitude();
        String pickupArea = rideRequest.getPickupArea();
        String destinationArea = rideRequest.getDestinationArea();

        Ride existingRide = new Ride();

        List<Driver> availableDrivers = driverService.getAvailableDrivers(pickupLatitude, pickupLongitude, 5, existingRide);
        Driver nearestDriver = driverService.findNearestDriver(availableDrivers, pickupLatitude, pickupLongitude);

        if (nearestDriver == null) {
            throw new DriverException("No available driver found nearby.");
        }

        return createRideRequest(user, nearestDriver, pickupLatitude, pickupLongitude,
                destinationLatitude, destinationLongitude, pickupArea, destinationArea);
    }

    @Override
    public Ride createRideRequest(User user, Driver nearestDriver, double pickupLatitude, double pickupLongitude,
                                  double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea)
            throws DriverException {

        Ride ride = new Ride();
        ride.setDriver(nearestDriver);
        ride.setUser(user);
        ride.setPickupLatitude(pickupLatitude);
        ride.setPickupLongitude(pickupLongitude);
        ride.setDestinationLatitude(destinationLatitude);
        ride.setDestinationLongitude(destinationLongitude);
        ride.setStatus(RideStatus.REQUESTED);
        ride.setPickupArea(pickupArea);
        ride.setDestinationArea(destinationArea);

        return rideRepository.save(ride);
    }

    @Override
    public void acceptRide(Integer rideId) throws RideException {
        Ride ride = findRideById(rideId);

        ride.setStatus(RideStatus.ACCEPTED);
        Driver driver = ride.getDriver();
        driver.setCurrentRide(ride);

        int otp = new Random().nextInt(9000) + 1000; // Generate 4-digit OTP
        ride.setOtp(otp);

        driverRepostary.save(driver);
        rideRepository.save(ride);
    }

    @Override
    public void declineRide(Integer rideId, Integer driverId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.getDeclinedDrivers().add(driverId);

        List<Driver> availableDrivers = driverService.getAvailableDrivers(
            ride.getPickupLatitude(), ride.getPickupLongitude(), 5, ride);

        Driver nearestDriver = driverService.findNearestDriver(availableDrivers, ride.getPickupLatitude(), ride.getPickupLongitude());

        if (nearestDriver != null) {
            ride.setDriver(nearestDriver);
            rideRepository.save(ride);
        } else {
            throw new RideException("No alternative driver available.");
        }
    }

    @Override
    public void startRide(Integer rideId, int otp) throws RideException {
        Ride ride = findRideById(rideId);
        if (otp != ride.getOtp()) {
            throw new RideException("Invalid OTP provided.");
        }

        ride.setStatus(RideStatus.STARTED);
        ride.setStartTime(LocalDateTime.now());
        rideRepository.save(ride);
    }

    @Override
    public void completeRide(Integer rideId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.setStatus(RideStatus.COMPLETED);
        ride.setEndTime(LocalDateTime.now());

        double distance = calculators.calculateDistance(
            ride.getPickupLatitude(), ride.getPickupLongitude(),
            ride.getDestinationLatitude(), ride.getDestinationLongitude()
        );
        long duration = Duration.between(ride.getStartTime(), ride.getEndTime()).toMillis();

        double fare = calculators.calculateFare(distance);
        ride.setDistance(Math.round(distance * 100.0) / 100.0);
        ride.setFare((int) Math.round(fare));
        ride.setDuration(duration);

        Driver driver = ride.getDriver();
        driver.getRides().add(ride);
        driver.setCurrentRide(null);
        driver.setTotalRevenueInteger((int) (driver.getTotalRevenueInteger() + Math.round(fare * 0.8)));

        driverRepostary.save(driver);
        rideRepository.save(ride);
    }

    @Override
    public void cancelRide(Integer rideId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.setStatus(RideStatus.CANCELLED);
        rideRepository.save(ride);
    }

    @Override
    public Ride findRideById(Integer rideId) throws RideException {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new RideException("Ride with ID " + rideId + " does not exist."));
    }
}
