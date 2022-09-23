package com.revature.services;

import java.util.List;

import com.revature.models.Cart;
import com.revature.models.CartItem;
import com.revature.models.User;

public interface CartService {
	//create cart because everyone has at least an empty cart
	Cart createCart(Cart cart);
	
	// list cart
	Cart getCart(User user);

	Cart getCartById(int id);

	//add an item to cart
	boolean addToCart(int productID);
	
	//remove an item from cart
	boolean removeFromCart(int productID);

	int updateCart(Cart cart);

	List<CartItem> getCartItems(Cart cart);

	void deleteCart(Cart cart);
	//maybe make a empty cart. Stretch goal, return here |
	//                                                   V
}
