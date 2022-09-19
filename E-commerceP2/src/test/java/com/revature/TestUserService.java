package com.revature;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

@SpringBootTest
public class TestUserService {

	@InjectMocks
	UserService userService;

	@Mock
	UserServiceImpl userServImp;

	@BeforeAll
	public void init() {
		
	}

	@Test
	public void registerUserTest() {

	}
	
	@Test
	public void loginUserTest() {
		
	}

	@Test
	public void logoutUserTest() {
		
	}
	
	@Test
	public void getUserByUserNameTest() {
		
	}
	
	@Test
	public void updateUserTest() {
		
	}
	
	@Test
	public void deleteUserTest() {
		
	}
}
