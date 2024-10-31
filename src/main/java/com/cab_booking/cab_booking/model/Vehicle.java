package com.cab_booking.cab_booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "license_plate")
	private String license_plate;
	
	@Column(name = "capacity")
	private String capacity;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Driver driver;
	
	
	
	
}
