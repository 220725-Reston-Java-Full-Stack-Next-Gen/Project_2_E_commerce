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
	
	@Query(value ="update users set user_name=?1, first_name=?2, last_name=?3, street_address=?4, city=?5, zip_code=?6, phone_number=?7, email=?8, date_modified=?9", nativeQuery = true)
	public boolean updateUser(String userName, String firstName, String lastName, String address, String city, int zipcode,String phoneNumber, String email, LocalDate dateModified );
	
	@Query(value ="select * from users where user_name=?1", nativeQuery = true)
	public User findbyUsername(String username);
	
}

