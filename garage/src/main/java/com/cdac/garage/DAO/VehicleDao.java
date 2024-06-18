package com.cdac.garage.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.garage.entities.User;
import com.cdac.garage.entities.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {
	
	@Query("select v from Vehicle v where v.user.uName = :username")
	public List<Vehicle> getAllVehiclesOfUser(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query("delete from Vehicle v where v.user = :User")
	public void removeAllVehiclesOfUser(@Param("User") User user);
	
	@Query("select v from Vehicle v where v.vNumber = :vnumber")
	Optional<Vehicle> searchVehicleByNumber (@Param("vnumber") String vNumber);
}
