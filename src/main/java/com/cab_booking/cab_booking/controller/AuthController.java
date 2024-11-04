package com.cab_booking.cab_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.configuration.JwtUtil;
import com.cab_booking.cab_booking.model.Driver;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.model.domain.UserRole;
import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.UserRepostary;

import com.cab_booking.cab_booking.request.LoginRequest;
import com.cab_booking.cab_booking.request.SignupRequest;
import com.cab_booking.cab_booking.response.JwtResponse;
import com.cab_booking.cab_booking.service.CustomerUserDetails;
import com.cab_booking.cab_booking.service.driverService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepostary userRepository;
    private final DriverRepostary driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomerUserDetails customerUserDetailsService;
    private final driverService driverService;

    @Autowired
    public AuthController(UserRepostary userRepository, DriverRepostary driverRepository, 
                          PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, 
                          JwtUtil jwtUtil, CustomerUserDetails customerUserDetailsService,
                          driverService driverService) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerUserDetailsService = customerUserDetailsService;
        this.driverService = driverService;
    }
//api//auth//user//signup
    
    @PostMapping("/user/signup")
    public ResponseEntity<JwtResponse> signupHandler(@RequestBody SignupRequest req) {
        String email = req.getEmail();

        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserException("User already exists with email: " + email);
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());
        
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(encodedPassword);
        createdUser.setFullName(req.getFullName());
        createdUser.setMobile(req.getMobile());
        createdUser.setRole(UserRole.USER);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);
        
        JwtResponse jwtResponse = new JwtResponse(jwt, true, false, null, UserRole.USER, "Account created successfully: " + savedUser.getFullName());

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
    
  //api//auth//driver/signin
    @PostMapping("/driver/signup")
    public ResponseEntity<JwtResponse> driverSignupHandler(@RequestBody DriverSignupRequest driverSignupRequest) {
        // Check if driver already exists
    	Driver driver = driverRepository.findByEmail(driverSignupRequest.getEmail());
        if (driverRepository.findByEmail(req.getEmail()).isPresent()) {
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setAuthenticated(false);
            jwtResponse.setError(true);
            jwtResponse.setErrorDetails("Email already in use with another account");
            return new ResponseEntity<>(jwtResponse, HttpStatus.BAD_REQUEST);
        }

        // Register new driver
        Driver createdDriver = driverService.registerDriver(req);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                createdDriver.getEmail(), req.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        JwtResponse jwtResponse = new JwtResponse(jwt, true, false, null, UserRole.DRIVER,  "Account created successfully: " + createdDriver.getName());

        return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
    }

    //api//auth//signin
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody LoginRequest req) {
        Authentication authentication = authenticate(req.getEmail(), req.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        JwtResponse jwtResponse = new JwtResponse(jwt, true, false, null, UserRole.USER, "Login successful");

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
