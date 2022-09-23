package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.repos.CartRepo;
import com.revature.repos.ProductRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
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
	
	@Override
	public boolean addToCart(int ProductID) {
		return cartRepo.save(ProductID);
	}
	
	@Override
	public boolean removeFromCart(int ProductID) {
		return cartRepo.delete(ProductID);	
	}

}
