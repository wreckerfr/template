package com.cdac.garage.service;

import java.util.List;

import com.cdac.garage.DTO.ApiResponse;
import com.cdac.garage.entities.Vehicle;

public interface VehicleService {
	
	public ApiResponse addVehicle(Vehicle v, Long id);
	
	public ApiResponse removeAllUserVehicles(String u);
	
	public List<Vehicle> fetchAllUserVehicles(String u);
	
}
