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
		return userTarget;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		User u = userRepo.save(user);
		userRepo.updateUser(u.getUserName(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getAddress(), u.getCity(), u.getZipcode(), u.getPhoneNumber(), u.getEmail(), u.getDateModified());
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		User u = userRepo.save(user);
		userRepo.delete(u);
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
