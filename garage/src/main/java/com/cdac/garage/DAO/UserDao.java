package com.cdac.garage.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.garage.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("select u from User u where uName = :username")
	public Optional<User> getUserByUsername(@Param("username") String username);
	
}
