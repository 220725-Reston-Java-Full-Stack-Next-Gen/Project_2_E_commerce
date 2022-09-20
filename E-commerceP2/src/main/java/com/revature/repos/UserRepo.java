package com.revature.repos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.models.UserRole;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query(value ="update users set user_name=?1, user_password=2?,first_name=?3, last_name=?4, street_address=?5, city=?6, zip_code=?7, phone_number=?8, email=?9, date_modified=?10", nativeQuery = true)
	public boolean updateUser(String userName,String password,String firstName, String lastName, String address, String city, int zipcode,String phoneNumber, String email, LocalDate dateModified );
	
	@Query(value ="select * from users where user_name=?1", nativeQuery = true)
	public User findbyUsername(String username);
	
}

