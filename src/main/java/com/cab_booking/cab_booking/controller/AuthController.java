package com.cab_booking.cab_booking.controller;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.grammars.hql.HqlParser.SecondContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab_booking.cab_booking.Expection.UserException;
import com.cab_booking.cab_booking.model.User;
import com.cab_booking.cab_booking.model.domain.UserRole;
import com.cab_booking.cab_booking.repositary.DriverRepostary;
import com.cab_booking.cab_booking.repositary.UserRepostary;
import com.cab_booking.cab_booking.request.LoginRequest;
import com.cab_booking.cab_booking.request.SignupRequest;
import com.cab_booking.cab_booking.response.JwtResponse;
import com.cab_booking.cab_booking.service.CustomerUserDetails;

import io.jsonwebtoken.security.Password;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private UserRepostary userRepostary;
	private DriverRepostary driverRepostary;
	private PasswordEncoder  passwordEncoder;
	private DriverService driverService;
	
   private JwtUtil	 jwtUtil;
   private CustomerUserDetails customerUserDetails;
	
	public  AuthController(UserRepostary userRepostary,DriverRepostary driverRepostary,PasswordEncoder  passwordEncoder) {
		this.userRepostary =userRepostary;
		this.driverRepostary=driverRepostary;
		this.passwordEncoder =passwordEncoder;
	}
	
	public ResponseEntity<JwtResponse> signupHandler(@RequestBody SignupRequest req){
		
		String email = req.getEmail();
		String fullName = req.getFullName();
		String mobile = req.getMobile();
		String password = req.getPassword();
		
		if(user!=null) {
			throw new UserException("user already with email"+ email);
		}
		
		String encodePassword = passwordEncoder.encode(password);
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(encodePassword);
		createdUser.setFullName(fullName);
		createdUser.setMobile(mobile);
		createdUser.setRole(UserRole.USER);
		
		
		User savedUser = userRepostary.save(createdUser);
	//	Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
		 SecurityContextHolder.getContext()
		 .setAuthentication(authentication);
		 
		 String jwt = jwtUtil.generateJwtToken(authentication);
		
		 JwtResponse jwtResponse  = new JwtResponse();
		 JwtResponse.setJwt(jwt);
		 JwtResponse.setAuthencation(true);
		 JwtResponse.setError(false);
		 JwtResponse.setErrorDetails(null);
		 JwtResponse.setType(UserRole.USER);
		 JwtResponse.setMessage(" Account created successful"+ savedUser.getFullName());
		
		 return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
	}
	
	public ResponseEntity<JwtResponse> signin(@RequestBody LoginRequest  req){
		String username = req.getEmail();
		String password = req.getPassword();
	}
	
	Authentication authentication = authenticate(Password ,username);
	 SecurityContextHolder.getContext().setAuthication(authentication);
	String jwt =jwtUtil.generateJwtToken(authentication);
	JwtResponse jwtResponse  = new JwtResponse();
	 JwtResponse.setJwt(jwt);
	 JwtResponse.setAuthencation(true);
	 JwtResponse.setError(false);
	 JwtResponse.setErrorDetails(null);
	 JwtResponse.setType(UserRole.USER);
	 JwtResponse.setMessage(" Account lo successful"+ savedUser.getFullName());
	 return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
}


private Authentication authenticate(String password,String username) {
	UserDetails userDetails = customerUserDetails.loadUserByUsername(username);
}


if(userDetails==null) {
	throw new BadCredentialsException(" invalid username or password from");
}
if(!passwordEncoder.match(password,userDetails.getPasswords())) {
	throw new BadCredinatialsException("invalid username or password");

}

return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
}
