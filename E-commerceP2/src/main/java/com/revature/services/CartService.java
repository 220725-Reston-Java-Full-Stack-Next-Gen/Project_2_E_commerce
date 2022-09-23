package com.revature.services;

import java.util.List;

import com.revature.models.Cart;
<<<<<<< HEAD
=======
import com.revature.models.CartItem;
>>>>>>> Raphael
import com.revature.models.User;

public interface CartService {
	//create cart because everyone has at least an empty cart
	Cart createCart(Cart cart);
	
	// list cart
<<<<<<< HEAD
	List<Cart> getCart(int userID);
	
	// list cart items
	List<Cart> getCartItems(int cartID);
	
=======
	Cart getCart(User user);

	Cart getCartById(int id);

>>>>>>> Raphael
	//add an item to cart
	boolean addToCart(int productID);
	
	//remove an item from cart
	boolean removeFromCart(int productID);
<<<<<<< HEAD
	
=======

	int updateCart(Cart cart);

	List<CartItem> getCartItems(Cart cart);

	void deleteCart(Cart cart);
>>>>>>> Raphael
	//maybe make a empty cart. Stretch goal, return here |
	//                                                   V
}
