package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {

	// user login
	public User login(String username, String password);

	// user logout
	public boolean logout();

	// user create/register
	public boolean registerUser(User user);
	
	//get user by user name
	public User getUserByUsername(String username);
	
	// get user by user id
	public User getUserById(int id);
	
	//get all users
	public List<User> getAllUsers();	
	
	// user update
	public int updateUser(User user);

	// user delete
	public boolean deleteUser(User user);
}
