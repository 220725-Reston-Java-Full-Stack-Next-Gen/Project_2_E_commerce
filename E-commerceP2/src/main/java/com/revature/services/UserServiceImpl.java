package com.revature.services;

import java.util.List;

import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.repos.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
<<<<<<< HEAD


	public UserServiceImpl(UserRepo repo) {
		this.userRepo = repo;
	}
	

=======
>>>>>>> Raphael
	@Override
	public User login(String username, String password) {

		User userTarget = getUserByUsername(username);

		if (userTarget != null) {
			if (userTarget.getUserName().equals(username) && userTarget.getPassword().equals(password)) {
				return userTarget;
			} else {
				throw new InvalidCredentialsException("Invalid Username/Password combo. Please try again.");
			}
		} else {
			throw new UserNotFoundException("No user with that username was found. Please try again.");
		}
	}

	@Override
	public boolean logout() {

		return false;
	}

	@Override
	public boolean registerUser(User user) {

		
		int uId = userRepo.save(user).getId();
		return (uId > 0) ? true:false;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		User userTarget = userRepo.findbyUsername(username);
<<<<<<< HEAD
=======
		System.out.println(userTarget);
>>>>>>> Raphael
		return userTarget;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		//User user = userRepo.save(user);
		return userRepo.updateUser(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getCity(), user.getState(), user.getZipcode(), user.getPhoneNumber(), user.getEmail(), user.getDateModified(), user.getId());
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		 userRepo.delete(user);
		 return true;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

}
