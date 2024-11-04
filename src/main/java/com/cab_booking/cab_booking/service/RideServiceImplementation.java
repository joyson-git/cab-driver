package com.cab_booking.cab_booking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import javax.management.Notification;

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
	
	
//	@Autowired
//	private NotificationRepository notificationRepository;
	
	
	@Override
	public Ride requestRide(RideRequest rideRequest, User user) throws DriverException {
		double picuplatitude = rideRequest.getPickupLatitude();
		double picupLongitude = rideRequest.getPickupLongitude();
		double destinationLongitude = rideRequest.getDestinationLLongitude();
		double destinationLongitude = rideRequest.getDestinationLLongitude();
		double pickupArea= rideRequest.getPickupLongitude();
		double destinationArea = rideRequest.getDestinationArea();
		
		Ride existingRide = new Ride();
		
		List<Driver> availableDrivers = driverService.getAvailableDrivers(picuplatitude, picupLongitude,5, existingRide);
		
		Driver nearestDriver= driverService.findNearestDriver(availableDrivers, picuplatitude,picupLongitude);
		
		if(nearestDriver==null) {
			throw new DriverException(" Driver not available");
		}
		
		System.out.print(" duration == before ride");
		
		
		Ride ride - createRideRequest(user, nearestDriver,
				picuplatitude, picupLongitude,
				destinationLongitude, destinationLongitude,
				pickupArea, destinationArea);
		return ride;
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
		
		
		System.out.print("----a"+ pickupLatitude);
		
		
		
		return rideRepository.save(ride);
	}

	@Override
	public void acceptRide(Integer rideId) throws RideException {
		Ride ride = findRideById(rideId);
		
		ride.setStatus(RideStatus.ACCEPTED);
		Driver driver = ride.getDriver();
		driver.setCurrentRide(ride);
		Random random = new Random();
		
		int otp = random.nextInt(9000)+100;
		ride.setOtp(otp);
		driverRepostary.save(driver);
		rideRepository.save(ride);
		
	}

	@Override
	public void declineRide(Integer rideId, Integer driverId) throws RideException {
		
		Ride ride = findRideById(rideId);
		System.out.print(ride.getId());
		 ride.getDeclinedDrivers().add(driverId);
		 
		 System.out.print(ride.getId()+""+rideId.getDeclineDriver());
		 
		 List<Driver> availableDrivers = driverService.getAvailableDrivers(ride.getPickupLatitude(),
				 rideId.getPickupLongitude(),5,ride);
		 
		 
		 Driver  nearDriver = driverService.findNearestDriver(availableDrivers,ride.getDestinationLatitude(),ride.getPickupLatitude(),ride.getPickupLongitude());
		 
		 ride.setDriver(nearDriver);
		 
		 rideRepository.save(ride);
		 
		
		
		
		
	}

	@Override
	public void startRide(Integer rideId, int option) throws RideException {
		
		Ride ride = findRideById(rideId);
		if(otp!=ride.getOtp()) {
			throw new RideException("please provude a valid otp");
		}
		
		ride.setStatus(RideStatus.STARTED);
		ride.setStartTime(LocalDateTime.now());
		rideRepository.save(ride);
		
		
		
		
	}

	@Override
	public void completeRide(Integer rideId) throws RideException {
		Ride ride=find RideById(rideld);
		ride.setStatus(RideStatus.COMPLETED);
		ride.setEndTime(LocalDateTime.now());;
		double distence=calculaters.calculate Distance(ride.getDestination Latitude(), ride.getDestinationLongitude(), ride.getPickupArea(),ride.getPickupLatitude(),getPickLongtitude());
		LocalDateTime start=ride.getStartTime();
		LocalDateTime end-ride.getEndTime();
		Duration duration = Duration.between(start, end);
		long milliSecond = duration.toMillis();	
		
		System.out.println("duration "+ milliSecond);
		double fare=calculaters.calculateFare(distence);
		
		ride.setDistence (Math.round(distence * 100.0) / 100.0);
		ride.setFare((int) Math.round(fare));
		ride.setDuration(milliSecond);
		ride.setEndTime(LocalDateTime.now());
		
		Driver driver =ride.getDriver();
		driver.getRides().add(ride);
		driver.setCurrentRide(null);
		// 80 driver // 20 company
		Integer drivers Revenue=(int) (driver.getTotal Revenue()+ Math.round(fare*0.8));
		driver.setTotal Revenue (drivers Revenue);
		System.out.println("drivers revenue -- "+drivers Revenue);
		driverRepository.save(driver);
		ride Repository.save(ride);
		
		
		
	}

	@Override
	public void cancelRide(Integer rideId) throws RideException {
		Ride ride=findRideById(rideld);
		ride.setStatus(RideStatus.CANCELLED);
		ride Repository.save(ride);
		}
		
	}

	@Override
	public Ride findRideById(Integer rideId) throws RideException {
			Optional<Ride> ride=rideRepository.findById(rideld);
			if(ride.isPresent()) {
			return ride.get();
			}
			throw new RideException("ride not exist with id "+rideld);
			}
	
	
	

}
