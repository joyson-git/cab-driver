package com.cab_booking.cab_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cab_booking.cab_booking.Expection.RideException;
import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.dtos.RideDTO;
import com.cab_booking.cab_booking.dtos.mapper.DtoMapper;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.request.RideRequest;
import com.cab_booking.cab_booking.request.StartRideRequest;
import com.cab_booking.cab_booking.response.MessageResponse;
import com.cab_booking.cab_booking.service.RiderService;
import com.cab_booking.cab_booking.service.UserService;



@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RiderService rideService;

    @Autowired
    private driverService driverService;

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public ResponseEntity<RideDTO> userRequestRideHandler(@RequestBody RideRequest rideRequest, @RequestHeader("Authorization") String jwt) throws UserException, RideException {
        User user = userService.getReqUserProfile(jwt);
        Ride ride = rideService.requestRide(rideRequest, user);
        RideDTO rideDto = DtoMapper.toRideDTO(ride);
        return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<MessageResponse> acceptRideHandler(@PathVariable Integer rideId) throws UserException, RideException {
        rideService.acceptRide(rideId);
        MessageResponse res = new MessageResponse("Ride Accepted By Driver");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/start")
    public ResponseEntity<MessageResponse> rideStartHandler(@PathVariable Integer rideId, @RequestBody StartRideRequest req) throws UserException, RideException {
        rideService.startRide(rideId, req.getOtp());
        MessageResponse res = new MessageResponse("Ride is started");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<MessageResponse> rideCompleteHandler(@PathVariable Integer rideId) throws UserException, RideException {
        rideService.completeRide(rideId);
        MessageResponse res = new MessageResponse("Ride Is Completed. Thank You For Booking Cab");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideDTO> findRideByIdHandler(@PathVariable Integer rideId, @RequestHeader("Authorization") String jwt) throws UserException, RideException {
        User user = userService.getReqUserProfile(jwt);
        Ride ride = rideService.findRideById(rideId);
        RideDTO rideDto = DtoMapper.toRideDTO(ride);
        return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
    }
}
