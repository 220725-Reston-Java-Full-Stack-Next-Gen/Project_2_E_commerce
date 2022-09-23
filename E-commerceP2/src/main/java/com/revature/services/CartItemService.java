package com.revature.services;

import com.revature.models.Cart;
import com.revature.models.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addItem(CartItem item);
    CartItem getCartItem(int cartItemId);
    List<CartItem> getCartItems(Cart cart);
    int update(CartItem cartItem);
    void delete(CartItem cartItem);
}
