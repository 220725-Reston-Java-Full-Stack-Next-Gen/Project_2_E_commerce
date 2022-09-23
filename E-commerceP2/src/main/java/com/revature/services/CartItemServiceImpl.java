package com.revature.services;

import com.revature.models.Cart;
import com.revature.models.CartItem;
import com.revature.repos.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public CartItem addItem(CartItem item) {
        return cartItemRepo.save(item);
    }

    @Override
    public CartItem getCartItem(int cartItemId) {
        return cartItemRepo.getCartItem(cartItemId);
    }

    @Override
    public List<CartItem> getCartItems(Cart cart) {
        return cartItemRepo.getCartItems(cart.getCartID());
    }

    @Override
    public int update(CartItem cartItem) {
        return cartItemRepo.updateItem(cartItem.getDateModified(), cartItem.getProductQuantity(), cartItem.getCartItemID());
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemRepo.delete(cartItem);
    }
}
