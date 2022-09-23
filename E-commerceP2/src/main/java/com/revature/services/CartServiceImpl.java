package com.revature.services;

import java.util.List;

<<<<<<< HEAD
=======
import com.revature.models.CartItem;
import com.revature.repos.CartItemRepo;
>>>>>>> Raphael
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.repos.CartRepo;
<<<<<<< HEAD
import com.revature.repos.ProductRepo;
=======
>>>>>>> Raphael

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
<<<<<<< HEAD
	private ProductRepo productRepo;
	
	@Override
	public Cart createCart(Cart cart) {
		return cartRepo.saveNew(cart);
	}

	@Override
	public List<Cart> getCart(int userID) {
		return cartRepo.getCart(userID);
	}	
	
	@Override
	public List<Cart> getCartItems(int cartID) {
		return cartRepo.getCart(cartID);
	}
	
=======

	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public Cart getCart(User user) {
		return cartRepo.getCart(user.getId());
	}

	@Override
	public Cart getCartById(int id) {
		return cartRepo.getCartByCartID(id);
	}

>>>>>>> Raphael
	@Override
	public boolean addToCart(int ProductID) {
		return cartRepo.save(ProductID);
	}
	
	@Override
	public boolean removeFromCart(int ProductID) {
		return cartRepo.delete(ProductID);	
	}

<<<<<<< HEAD
=======
	@Override
	public int updateCart(Cart cart) {
		return cartRepo.updateCart(cart.getDateModified(), cart.getOrderQuantity(), cart.getCartID());
	}

	@Override
	public List<CartItem> getCartItems(Cart cart) {
		return cartItemRepo.getCartItems(cart.getCartID());
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepo.delete(cart);
	}


>>>>>>> Raphael
}
