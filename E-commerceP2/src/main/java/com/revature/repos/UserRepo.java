package com.revature.repos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;
import com.revature.models.UserRole;

public interface UserRepo extends JpaRepository<User, Integer> {
	

	
	public boolean updateUser(String userName, String firstName, String lastName, String address, String city, int zipcode,String phoneNumber, String email, UserRole userRole, LocalDate dateCreated, LocalDate dateModified );
	
	public User findbyUsername(String username);
	
}

