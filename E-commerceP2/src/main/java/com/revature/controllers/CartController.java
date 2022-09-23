package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.services.CartService;

@Controller
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/cart")
public class CartController {
	//since this controller relies on the service layer, we need to inject this dependency into this class:
	@Autowired
	private CartService cartServ;
	
	@PostMapping("/create-cart")
	public Cart createCart(@RequestParam Cart cart) {
		return cartServ.createCart(cart);
	}
	
	@GetMapping("/get-cart")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
	public @ResponseBody List<Cart> getCart(@RequestParam int userID){
		return cartServ.getCart(userID);
	}
	
	@GetMapping("/get-cart-items")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
	public @ResponseBody List<Cart> getCartItems(@RequestParam int cart_id){
		return cartServ.getCartItems(cart_id);
	}
	
	@GetMapping("/get-product-item")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
	public @ResponseBody List<Cart> getProductItems(@RequestParam int product_id){
		return cartServ.getProductItems(product_id);
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
