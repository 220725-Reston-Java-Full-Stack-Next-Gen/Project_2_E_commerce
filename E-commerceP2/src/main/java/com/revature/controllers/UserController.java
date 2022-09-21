package com.revature.controllers;

import static com.revature.utils.ClientMessageUtil.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ClientMessage registerAccount(@RequestBody User user) {
		return userService.registerUser(user) ? REGISTATION_SUCCESSFUL : REGISTATION_FAILED;
	}
	
	public ClientMessage loginUser(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		String userName = req.getParameter("username");
		String psw = req.getParameter("password");
		
		session.setAttribute("userName",userName);
		
		return userService.login(userName, psw) ? LOGIN_SUCCESSFUL:LOGIN_FAILED;
	
	
	}
	
	
	
}
