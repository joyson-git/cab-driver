package com.cab_booking.cab_booking.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.wiring.ClassNameBeanWiringInfoResolver;
import org.springframework.data.geo.Distance;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.License;
import com.cab_booking.cab_booking.model.Ride;
import com.cab_booking.cab_booking.model.Vehicle;
import com.cab_booking.cab_booking.model.domain.RideStatus;
import com.cab_booking.cab_booking.model.domain.UserRole;
import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.LicenseRepositary;
import com.cab_booking.cab_booking.repositary.RideRepository;
import com.cab_booking.cab_booking.repositary.VechileRepository;
import com.cab_booking.cab_booking.request.DriverSigupRequest;


@Service
public class driverServiceImplementation implements driverService {

	@Autowired
	private DriverRepostary driverRepostary;
	
	@Autowired
	private Calculaters DistenceCalculator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private VechileRepository vechileRepository;
	
	@Autowired
	private LicenseRepositary licenseRepositary;
	
	@Autowired
	private RideRepository rideRepositary;

	@Override
	public Driver registerDriver(DriverSigupRequest driverSignupRequest) {
		License license = driverSignupRequest.getLicense();
		Vehicle vehicle = driverSignupRequest.getVehicle();
		
		License createdLicense = new License();
		
		createdLicense.setLicenseState(license.getLicenseState());
		createdLicense.setLicenseNumber(license.getLicenseNumber());
		createdLicense.setLicenseExpirationDate(license.getLicenseExpirationDate());
		createdLicense.setId(license.getId());
		
		
		License savedLicense = licenseRepositary.save(createdLicense);
		
		
		Vehicle createdVehicle = new Vehicle();
		
		createdVehicle.setCapacity(vehicle.getCapacity());
		createdVehicle.setColor(vehicle.getColor());
		createdVehicle.setId(vehicle.getId());
		createdVehicle.setLicense_plate(vehicle.getLicense_plate());
		createdVehicle.setMake(vehicle.getMake());
		createdVehicle.setModel(vehicle.getModel());
		createdVehicle.setYear(vehicle.getYear());
		
		Vehicle savedVehicle = vechileRepository.save(createdVehicle);
		
		Driver driver = new Driver();
		
		String encodePassword = passwordEncoder.encode(DriverSigupRequest.getPassword());
	
	 driver.setEmail(driverSignupRequest.getEmail());
	 driver.setName(driverSignupRequest.getName());
	 driver.setMobile(driverSignupRequest.getMobile());
	 driver.setPassword(encodePassword);
	 driver.setLicense(savedLicense);
	 driver.setVehicle(savedVehicle);
	 driver.setRole(UserRole.DRIVER);
	 
	 driver.setLatitude(driverSignupRequest.getLatitude());
	 driver.setLongitude(driverSignupRequest.getLongitude());
	 
	 Driver createDriver = driverRepostary.save(driver);
	 
	 savedLicense.setDriver(createDriver);
	 savedVehicle.setDriver(createDriver);
	 
	 
	 licenseRepositary.save(savedLicense);
	 vechileRepository.save(savedVehicle);
	 
	 return createDriver;
	}

	@Override
	public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
	List<Driver> allDrivers = driverRepostary.findAll();
	
	List<Driver> avaiableDrivers = new ArraysList<>();
	
	for(Driver driver:allDrivers) {
		if(driver.getCurrentRide()!=null && driverService.getCurrentRide().getStatus()!=RideStatus.COMPLETED) {
			continue;
		}
		
		if(ride.getDeclinedDrivers().contains(driver.getId())) {
			System.out.print(" its contains");
			continue;
		}
		
		double  driverLatitude = driver.getLatitude();
		double  driverLongitude = driver.getLongitude();
		
		
		
		double distence = DistenceCalculator.calculateDistance(driverLatitude,driverLongitude,pickupLatitude,pickupLongitude){
			avaiableDrivers.add(driver);
		}
	}
	
	

		return avaiableDrivers;
	}

	@Override
	public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude ) {
		double min = Double.MAX_VALUE;
		Driver neaDriver = null;
		
		for(Driver driver:availableDrivers) {
			double driverLatitude = driver.getLatitude();
			double driverLongitude = driver.getLongitude();
			
			
			double distence = DistenceCalculator.calculateDistance(pickupLatitude,pickupLongitude,driverLatitude,driverLongitude)
		
		if(min>distence) {
			min= distence;
			nearestDriver= driver;
		}
		
		}
		
		return nearestDriver;
	}

	@Override
	public Driver getRequestedDriver(String jwt) throws DriverException {
		
		
		String email = jwtUtil.getEmailFromJwt(jwt);
		Driver driver = driverRepostary.findByEmail(email);
		if(driver==null) {
			throw new DriverException("drive not exists"+ email);
		}
		return driver;
	}

	@Override
	public Ride getDriverCurrentRide(Integer driverId) throws DriverException {
		Driver driver = findDriverById(driverId);
		return driver.getCurrentRide();
	//	return null;
	}

	@Override
	public List<Ride> getAllocatedRides(Integer driverId) throws DriverException {
		List<Ride> allocatedRides = driverRepostary.getAllocatedRides(driverId);
		return allocatedRides;
	}

	@Override
	public Driver findDriverById(Integer driverId) throws DriverException {
		Optional<Driver> opt = driverRepostary.findById(driverId);
		if(opt.isPresent()) {
			return opt.get();
		}
		 throw  new DriverException(" driver not exists with id"+ driverId); 
	}

	@Override
	public List<Ride> getCompletedRides(Integer driverId) throws DriverException {
		List<Ride> rides = driverRepostary.getCompleteRides(driverId);
		return rides;
	}
	
}
