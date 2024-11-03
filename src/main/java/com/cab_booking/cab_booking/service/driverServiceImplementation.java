package com.cab_booking.cab_booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cab_booking.cab_booking.Expection.DriverException;
import com.cab_booking.cab_booking.configuration.JwtUtil;
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
    private Calculaters distanceCalculator;

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

        License savedLicense = licenseRepositary.save(createdLicense);

        Vehicle createdVehicle = new Vehicle();
        createdVehicle.setCapacity(vehicle.getCapacity());
        createdVehicle.setColor(vehicle.getColor());
        createdVehicle.setLicense_plate(vehicle.getLicense_plate());
        createdVehicle.setMake(vehicle.getMake());
        createdVehicle.setModel(vehicle.getModel());
        createdVehicle.setYear(vehicle.getYear());

        Vehicle savedVehicle = vechileRepository.save(createdVehicle);

        Driver driver = new Driver();
        String encodedPassword = passwordEncoder.encode(driverSignupRequest.getPassword());

        driver.setEmail(driverSignupRequest.getEmail());
        driver.setName(driverSignupRequest.getName());
        driver.setMobile(driverSignupRequest.getMobile());
        driver.setPassword(encodedPassword);
        driver.setLicense(savedLicense);
        driver.setVehicle(savedVehicle);
        driver.setRole(UserRole.DRIVER);
        driver.setLatitude(driverSignupRequest.getLatitude());
        driver.setLongitude(driverSignupRequest.getLongitude());

        Driver createdDriver = driverRepostary.save(driver);
        savedLicense.setDriver(createdDriver);
        savedVehicle.setDriver(createdDriver);

        licenseRepositary.save(savedLicense);
        vechileRepository.save(savedVehicle);

        return createdDriver;
    }

    @Override
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
        List<Driver> allDrivers = driverRepostary.findAll();
        List<Driver> availableDrivers = new ArrayList<>();

        for (Driver driver : allDrivers) {
            if (driver.getCurrentRide() != null && driver.getCurrentRide().getStatus() != RideStatus.COMPLETED) {
                continue;
            }
            if (ride.getDeclinedDrivers().contains(driver.getId())) {
                continue;
            }

            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();
            double distance = distanceCalculator.calculateDistance(driverLatitude, driverLongitude, pickupLatitude, pickupLongitude);

            if (distance <= radius) {
                availableDrivers.add(driver);
            }
        }

        return availableDrivers;
    }

    @Override
    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {
        double minDistance = Double.MAX_VALUE;
        Driver nearestDriver = null;

        for (Driver driver : availableDrivers) {
            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();
            double distance = distanceCalculator.calculateDistance(pickupLatitude, pickupLongitude, driverLatitude, driverLongitude);

            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }

    @Override
    public Driver getRequestedDriver(String jwt) throws DriverException {
        String email = jwtUtil.getEmailFromJwt(jwt);
        Driver driver = driverRepostary.findByEmail(email);

        if (driver == null) {
            throw new DriverException("Driver not exists: " + email);
        }
        return driver;
    }

    @Override
    public Ride getDriverCurrentRide(Integer driverId) throws DriverException {
        Driver driver = findDriverById(driverId);
        return driver.getCurrentRide();
    }

    @Override
    public List<Ride> getAllocatedRides(Integer driverId) throws DriverException {
        return rideRepositary.getAllocatedRides(driverId);
    }

    @Override
    public Driver findDriverById(Integer driverId) throws DriverException {
        Optional<Driver> driverOpt = driverRepostary.findById(driverId);
        return driverOpt.orElseThrow(() -> new DriverException("Driver not exists with id: " + driverId));
    }

    @Override
    public List<Ride> getCompletedRides(Integer driverId) throws DriverException {
        return rideRepositary.getCompleteRides(driverId);
    }
}
