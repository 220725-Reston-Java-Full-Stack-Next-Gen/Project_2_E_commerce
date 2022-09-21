package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.services.CartService;

@Controller
@RequestMapping("/api")
public class CartController {
	//since this controller relies on the service layer, we need to inject this dependency into this class:
	@Autowired
	private CartService cartServ;
	
	@GetMapping("/cart")
	public List<Cart> getCart(@RequestParam User user){
		return cartServ.getCart(user);
	}
	
	@PutMapping("/add-to-cart")
	public boolean addToCart(@RequestParam int ProductID) {
		return cartServ.addToCart(ProductID);
	}
	
	@DeleteMapping("/remove-from-cart")
	public boolean removeFromCart(@RequestParam int ProductID) {
		return cartServ.removeFromCart(ProductID);
	}
}
