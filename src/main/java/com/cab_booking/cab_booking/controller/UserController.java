package com.cab_booking.cab_booking.controller;

import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer userId) throws UserException {
        System.out.println("Finding user by ID");
        User createdUser = userService.findUserById(userId);
        return new ResponseEntity<>(createdUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rides/completed")
    public ResponseEntity<List<Ride>> getCompletedRidesHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getReqUserProfile(jwt);
        List<Ride> rides = userService.completedRides(user.getId());
        return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getReqUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getReqUserProfile(jwt);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
