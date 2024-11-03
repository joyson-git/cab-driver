package com.cab_booking.cab_booking.configuration;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    
    private final SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setIssuer("code with Zosh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 864000000)) // 10 days
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwt(String jwt) {
        jwt = jwt.substring(7); // Remove "Bearer " prefix
        Claims claims = Jwts.parser()
        		.setSigningKey(key)
                .build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}
