package com.revature.services;

import java.util.List;

import com.revature.models.Cart;
import com.revature.models.User;

public interface CartService {
	//create cart because everyone has at least an empty cart
	Cart createCart(Cart cart);
	
	// list cart
	List<Cart> getCart(int userID);
	
	//add an item to cart
	boolean addToCart(int productID);
	
	//remove an item from cart
	boolean removeFromCart(int productID);
	
	//maybe make a empty cart. Stretch goal, return here |
	//                                                   V
}
