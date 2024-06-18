package com.cdac.garage.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.garage.DTO.ApiResponse;
import com.cdac.garage.DTO.VehicleDto;
import com.cdac.garage.entities.Vehicle;
import com.cdac.garage.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehiServ;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getAllVehiclesOfUser(@PathVariable String username){
		List<Vehicle> vehiList = vehiServ.fetchAllUserVehicles(username);
		if(vehiList == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("could not fetch vehicle list"));
		}
		List<VehicleDto> vehiDtoList = vehiList.stream().map(v->mapper.map(v, VehicleDto.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(vehiDtoList);
		
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteAllVehiclesOfUser(@PathVariable String username){
		ApiResponse resp = vehiServ.removeAllUserVehicles(username);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewVehicle (@RequestBody VehicleDto vehiD){
		Vehicle vehi = mapper.map(vehiD, Vehicle.class);
		ApiResponse resp = vehiServ.addVehicle(vehi, vehiD.getUId());
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
}
