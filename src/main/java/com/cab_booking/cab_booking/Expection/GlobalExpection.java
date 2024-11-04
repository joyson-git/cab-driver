package com.cab_booking.cab_booking.Expection;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExpection {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetail> userExceptionHandler(UserException ue,WebRequest req){
	
		ErrorDetail err  = new ErrorDetail(ue.getMessage(), 
				req.getDescription(false), 
				LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err,HttpStatus.BAD_REQUEST);
	}
	 @ExceptionHandler(DriverException.class)
	    public ResponseEntity<ErrorDetail> driverExceptionHandler(DriverException de, WebRequest req) {
	        ErrorDetail err = new ErrorDetail(
	                de.getMessage(),
	                req.getDescription(false),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(RideException.class)
	    public ResponseEntity<ErrorDetail> rideExceptionHandler(RideException re, WebRequest req) {
	        ErrorDetail err = new ErrorDetail(
	                re.getMessage(),
	                req.getDescription(false),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(ConstructViolation.class)
	    public ResponseEntity<ErrorDetail> constructViolationHandler(ConstructViolation cv, WebRequest req) {
	        ErrorDetail err = new ErrorDetail(
	                cv.getMessage(),
	                req.getDescription(false),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	        
	        	String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorDetail> otherExceptionHandler(Exception ex, WebRequest req) {
	        ErrorDetail err = new ErrorDetail(
	                "An unexpected error occurred: " + ex.getMessage(),
	                req.getDescription(false),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}