package com.revature.controllers;

import static com.revature.utils.ClientMessageUtil.*;


import javax.servlet.http.HttpServletRequest;

import com.revature.exceptions.NotAuthenticatedException;
import com.revature.models.LoginForm;
import com.revature.models.LoginLog;
import com.revature.models.UserRole;
import com.revature.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.models.User;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/register")
	public @ResponseBody ClientMessage registerAccount(@RequestBody User user) {
		user.setDateCreated(LocalDate.now());
		user.setUserRole(new UserRole(2, "user"));
		System.out.println(user);
		return userService.registerUser(user) ? USER_REGISTRATION_SUCCESSFUL : USER_REGISTRATION_FAILED;
	}

	@PutMapping("/update")
	@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true", methods = RequestMethod.PUT, allowedHeaders = "*")
	public @ResponseBody ClientMessage updateAccount(@RequestBody User user, HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

		if (loggedInUser != null) {

			user.setDateModified(LocalDate.now());
			System.out.println(user);
			if (userService.updateUser(user) > 0) {

				request.getSession().setAttribute("loggedInUser", user);
				return USER_UPDATE_SUCCESSFUL;
			} else {
				return USER_UPDATE_FAILED;
			}
		} else {
			throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
		}
	}

	@PostMapping("/login")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
	public @ResponseBody User loginUser(@RequestBody LoginForm loginForm, HttpServletRequest req) {

		req.getSession().invalidate();

		String username = loginForm.getUserName();
		String password = loginForm.getPassword();

		System.out.println(username);
		User loggedInUser = userService.login(username, password);
		System.out.println(loggedInUser);
		req.getSession().setAttribute("loggedInUser", loggedInUser);

		if (loggedInUser != null) {
			LoginLog loginLog = new LoginLog();
			loginLog.setUser(loggedInUser);
			loginLog.setLoginTime(LocalDateTime.now());

			// add a log of this successful login
			int loginLogId = loginService.addLoginLog(loginLog);
			if (loginLogId > 0) {
				loginLog.setLoginLogID(loginLogId);
				req.getSession().setAttribute("loginLog", loginLog);
			}

		}
		return loggedInUser;
	}

	@PutMapping("/logout")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.PUT, allowedHeaders = "*")
	public @ResponseBody ClientMessage logoutUser(HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		System.out.println(loggedInUser);
		if (loggedInUser != null) {
			LoginLog loginLog = (LoginLog) request.getSession().getAttribute("loginLog");
			if (loginLog != null) {
				loginLog.setLogoutTime(LocalDateTime.now());
				System.out.println(loginLog);
				if (loginService.updateLoginLog(loginLog) > 0) {
					request.getSession().invalidate();
					return LOGOUT_SUCCESSFUL;
				} else {
					return LOGOUT_FAILED;
				}
			} else {
				return LOGOUT_FAILED;
			}
		} else {
			return NOT_AUTHENTICATED;
		}
	}
	
}
