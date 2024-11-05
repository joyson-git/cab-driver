package com.cab_booking.cab_booking.dtos.mapper;

import com.cab_booking.cab_booking.dtos.DriverDTO;
import com.cab_booking.cab_booking.dtos.RideDTO;
import com.cab_booking.cab_booking.dtos.UserDTO;

import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;

public class DtoMapper {

    public static DriverDTO toDriverDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();

        driverDTO.setEmail(driver.getEmail());
        driverDTO.setId(driver.getId());
        driverDTO.setLatitude(driver.getLatitude());
        driverDTO.setLongitude(driver.getLongitude());
        driverDTO.setMobile(driver.getMobile());
        driverDTO.setName(driver.getName());
        driverDTO.setRating(driver.getRating());
        driverDTO.setRole(driver.getRole());
        driverDTO.setVehicle(driver.getVehicle());

        return driverDTO;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setMobile(user.getMobile());
        userDTO.setName(user.getFullName());

        return userDTO;
    }

    public static RideDTO toRideDTO(Ride ride) {
        DriverDTO driverDTO = toDriverDTO(ride.getDriver());
        UserDTO userDTO = toUserDTO(ride.getUser());

        RideDTO rideDTO = new RideDTO();

        rideDTO.setDestinationLatitude(ride.getDestinationLatitude());
        rideDTO.setDestinationLongitude(ride.getDestinationLongitude());
        rideDTO.setDistance(ride.getDistance());
        rideDTO.setDriver(driverDTO);
        rideDTO.setDuration(ride.getDuration());
        rideDTO.setEndTime(ride.getEndTime());
        rideDTO.setFare(ride.getFare());
        rideDTO.setId(ride.getId());
        rideDTO.setPickupLatitude(ride.getPickupLatitude());
        rideDTO.setPickupLongitude(ride.getPickupLongitude());
        rideDTO.setStartTime(ride.getStartTime());
        rideDTO.setStatus(ride.getStatus());
        rideDTO.setUser(userDTO);
        rideDTO.setPickupArea(ride.getPickupArea());
        rideDTO.setDestinationArea(ride.getDestinationArea());
        rideDTO.setPaymentDetails(ride.getPaymentDetails());
        rideDTO.setOtp(ride.getOtp());

        return rideDTO;
    }
}
