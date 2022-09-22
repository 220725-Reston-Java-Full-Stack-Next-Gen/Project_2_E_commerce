package com.revature;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepo;
import com.revature.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

	@InjectMocks
	private static UserServiceImpl userServ;

	@Mock
	private static UserRepo userMockRepo;
	
	private static User u1, u2;
	
	static List<User> dummyDB;

	@BeforeAll
	public static void setUp() throws Exception{
		
		// Mockito setup
		//Mock depending class
		userMockRepo = Mockito.mock(UserRepo.class);
		//inject service with mocked class
		//userServ = new UserServiceImpl(userMockRepo);
		
		/*Dummy DB setup*/
		//we need to create a "fake database" of user objects
		u1 = new User(1,"t1","12345", "test1", "one", "test1Ave", "test1St", "test1Ct", 60412, "123456","test1@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now());
		u2 = new User(2,"t2","12345", "test2", "two", "test2Ave", "test2St", "test2Ct", 62322, "1234564343","test2@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now());
		
		dummyDB = new ArrayList<User>();
		dummyDB.add(u1);
		dummyDB.add(u2);
		
		System.out.println(dummyDB);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(userMockRepo).isNotNull();
		assertThat(userServ).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Register new User Test")
	public void registerUserTest() throws UserNotFoundException, SQLException{
		//Arrange step
		User u3 = new User("t3","12345", "test4", "three", "test3Ave", "test3St", "test3Ct", 64334, "1234564344","test3@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now());
		u3.setId(3);
		
		//Expected  Mock behavior 
		when(userMockRepo.save(u3)).thenReturn(u3);
		
		//act + assert step
		assertEquals(true, userServ.registerUser(u3));
		
	}
	
	@Test
	@Order(3)
	@DisplayName("3. login User Test")
	public void loginUserTest() {
		
		//Expected Mock behavior
		when(userMockRepo.findbyUsername("t1")).thenReturn(u1);
		when(userServ.login("t1", "12345")).thenReturn(u1);
		//act+assert step
		assertEquals(u1 , userServ.login("t1", "12345"));
		
	}

	@Test
	@Order(4)
	@DisplayName("2.logOut User Test")
	public void logoutUserTest() {
		
	}
	
	@Test
	@Order(5)
	@DisplayName("2. getUserByUserName Test")
	public void getUserByUserNameTest() {
		when(userServ.getUserByUsername("t1")).thenReturn(u1);
		
		assertEquals(u1, userServ.getUserByUsername("t1"));
	}
	
	@Test
	@Order(6)
	@DisplayName("2. update User Test")
	public void updateUserTest() {
		u2.setUserName("updtest2");
		u2.setPassword("54321");;
		
		when(userMockRepo.save(u2)).thenReturn(u2);
		when(userServ.getUserById(2)).thenReturn(u2);
		
		
		assertEquals(true, userServ.updateUser(u2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. delete User Test")
	public void deleteUserTest() {
		doNothing().when(userMockRepo).delete(u2);
		//act + assert step
		assertEquals(true, userServ.deleteUser(u2));
	}
}
