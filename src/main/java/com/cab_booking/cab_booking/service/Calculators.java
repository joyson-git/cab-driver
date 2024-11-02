package com.cab_booking.cab_booking.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class Calculators {
    
    private static final int EARTH_RADIUS = 6371; // Earth radius in kilometers
    
    public double calculateDistance(double sourceLat, double sourceLng, double destLat, double destLng) {
        double dLat = Math.toRadians(destLat - sourceLat);
        double dLng = Math.toRadians(destLng - sourceLng);
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(destLat)) *
                   Math.sin(dLng / 2) * Math.sin(dLng / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance;
    }
    
    public long calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.getSeconds();
    }
    
    public double calculateFare(double distance) {
        double baseFare = 11.0;
        double totalFare = baseFare * distance;
        return totalFare;
    }
}
