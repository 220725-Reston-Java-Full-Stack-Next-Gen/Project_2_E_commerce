package com.revature.services;

import java.util.List;

import com.revature.models.CartItem;
import com.revature.repos.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.repos.CartRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;

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

	@Override
	public boolean addToCart(int ProductID) {
		return cartRepo.save(ProductID);
	}
	
	@Override
	public boolean removeFromCart(int ProductID) {
		return cartRepo.delete(ProductID);	
	}

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


}
