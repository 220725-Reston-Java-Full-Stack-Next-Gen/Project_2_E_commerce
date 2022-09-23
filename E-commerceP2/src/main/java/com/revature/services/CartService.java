package com.revature.services;

import java.util.List;

import com.revature.models.Cart;
import com.revature.models.User;

public interface CartService {
	//create cart because everyone has at least an empty cart
	Cart createCart(Cart cart);
	
	// list cart
	List<Cart> getCart(int userID);
	
	// list cart items
	List<Cart> getCartItems(int cart_id);
	
	//add an item to cart
	boolean addToCart(int productID);
	
	//remove an item from cart
	boolean removeFromCart(int productID);

	List<Cart> getProductItems(int product_id);


	
	//maybe make a empty cart. Stretch goal, return here |
	//                                                   V
}
