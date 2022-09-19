package com.revature.services;

import com.revature.models.User;

public interface UserService {

	// user login
	public boolean login(String username, String password);

	// user logout
	public boolean logout();

	// user create/register
	public boolean registerUser(User user);
	
	//get user by user name
	public User getUserByUsername(String username);

	// user update
	public boolean updateUser(User user);

	// user delete
	public boolean deleteUser(User user);
}
