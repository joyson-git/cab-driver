package com.cab_booking.cab_booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab_booking.cab_booking.response.MessageResponse;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<MessageResponse> homeController() {
        MessageResponse msg = new MessageResponse("Welcome to Ola Backend System");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
