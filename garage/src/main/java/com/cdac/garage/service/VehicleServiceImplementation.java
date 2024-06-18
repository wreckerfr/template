package com.cdac.garage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.garage.DAO.UserDao;
import com.cdac.garage.DAO.VehicleDao;
import com.cdac.garage.DTO.ApiResponse;
import com.cdac.garage.entities.User;
import com.cdac.garage.entities.Vehicle;

@Service
@Transactional
public class VehicleServiceImplementation implements VehicleService {

	@Autowired
	VehicleDao vehiDao;

	@Autowired
	UserDao userDao;

	@Override
	public ApiResponse addVehicle(Vehicle v, Long id) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.findById(id);
		if(user.isEmpty()) {
			return new ApiResponse("User withid:"+id+" deos not exist!!!!");
		}else
			v.setUser(user.get());
		Optional<Vehicle> vehi = vehiDao.searchVehicleByNumber(v.getVNumber());
		if (vehi.isEmpty()) {
			vehiDao.save(v);
			return new ApiResponse("Vehicle registered successfully!!!!!");
		} else
			return new ApiResponse("Vehicle already exists");

	}

	@Override
	public ApiResponse removeAllUserVehicles(String u) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.getUserByUsername(u);
		if (user.isPresent()) {
			vehiDao.removeAllVehiclesOfUser(user.get());
			return new ApiResponse("All vehicles os user:" + u + " successfully removed!!!!");
		} else
			return new ApiResponse("User:" + u + " not found!!!!!");

	}

	@Override
	public List<Vehicle> fetchAllUserVehicles(String u) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.getUserByUsername(u);
		if (user.isPresent()) {
			return vehiDao.getAllVehiclesOfUser(u);
		} else
			return null;
	}

}
