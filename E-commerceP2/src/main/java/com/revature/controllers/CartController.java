package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.services.CartService;

@Controller
@RequestMapping("/api")
public class CartController {
	//since this controller relies on the service layer, we need to inject this dependency into this class:
	@Autowired
	private CartService cartServ;
}
