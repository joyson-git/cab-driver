package com.cab_booking.cab_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rides/")
public class RideController {
	@Autowired
	private RideService rideService;
	@Autowired
	private DriverService driverService;
	@Autowired
	private UserService userService;
}
