package com.revature.repos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.models.UserRole;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

	@Modifying
	@Query(value ="update users set user_name=?1, user_password=?2, first_name=?3, last_name=?4, street_address=?5, city=?6, state=?7, zip_code=?8, phone_number=?9, email=?10, date_modified=?11 where user_id = ?12", nativeQuery = true)
	public int updateUser(String userName, String password, String firstName, String lastName, String address, String city, String state, int zipcode, String phoneNumber, String email, LocalDate dateModified, int id);
	
	@Query(value ="select * from users where user_name=?1", nativeQuery = true)
	public User findbyUsername(String username);
	
}

