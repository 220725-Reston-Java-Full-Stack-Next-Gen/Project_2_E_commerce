package com.revature.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.exceptions.CartErrorException;
import com.revature.exceptions.NotAuthenticatedException;
import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.services.CartService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/api/cart")
public class CartController {
	//since this controller relies on the service layer, we need to inject this dependency into this class:
	@Autowired
	private CartService cartServ;

	@Autowired
	private CartItemService cartItemService;

	@PostMapping("/create-cart")
	public Cart createCart(@RequestParam Cart cart) {
		return cartServ.createCart(cart);
	}

	@GetMapping("/get-cart")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
	public @ResponseBody Cart getCart(HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser != null) {
			Cart currentUserCart = cartServ.getCart(loggedInUser);
			if (currentUserCart != null) {
				return currentUserCart;
			} else {
				throw new CartErrorException("Cart Not Found");
			}
		} else {
			throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
		}
	}

	@GetMapping("/get-cart-items")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
	public @ResponseBody List<CartItem> getCartItems(@RequestParam int cartID, HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser != null) {
			Cart cart = cartServ.getCartById(cartID);

			if (cart != null) {
				List<CartItem> items = cartItemService.getCartItems(cart);
				if (items != null) {
					return items;
				} else {
					throw new CartErrorException("Error when trying to retrieve cart items. Please try again");
				}
			} else {
				throw new CartErrorException("No cart was found for the user");
			}

		} else {
			throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
		}
	}

	@PostMapping("/add-to-cart")
	@CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
	public @ResponseBody Cart addToCart(@RequestBody Product product, HttpServletRequest request){
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

		System.out.println(request.getSession().getId());
		if (loggedInUser != null) {
			Cart currentUserCart = cartServ.getCart(loggedInUser);
			if (currentUserCart != null) {
				int currentCartQuantity = currentUserCart.getOrderQuantity();
				List<CartItem> cartItemList = cartItemService.getCartItems(currentUserCart);

				boolean contains = false;
				int currQuantity = 0;
				int currentCartItemsCount = cartItemList.size();
				CartItem prevCartItem = new CartItem();
				for (CartItem item : cartItemList) {

					if (item.getProduct().getProductID() == product.getProductID()) {

						contains = true;
						currQuantity = item.getProductQuantity();
						prevCartItem = item;
						break;
					}
				}

				if (contains) {

					prevCartItem.setCart(currentUserCart);

					prevCartItem.setProductQuantity(currQuantity +1);
					prevCartItem.setDateModified(LocalDateTime.now());
					System.out.println(prevCartItem);
					cartItemService.update(prevCartItem);
				} else {
					CartItem cartItem = new CartItem();
					cartItem.setCart(currentUserCart);

					cartItem.setCartItemNumber(currentCartItemsCount +1);
					cartItem.setDateCreated(LocalDateTime.now());
					cartItem.setProduct(product);
					cartItem.setProductQuantity(1);

					cartItemService.addItem(cartItem);
				}
				currentUserCart.setDateModified(LocalDateTime.now());
				currentUserCart.setOrderQuantity(currentCartQuantity +1);
				cartServ.updateCart(currentUserCart);

				return currentUserCart;
			} else {
				Cart cart = new Cart();
				cart.setDateCreated(LocalDateTime.now());
				cart.setUser(loggedInUser);
				cart.setOrderQuantity(1);

				Cart newCart =  cartServ.createCart(cart);

				if (newCart != null) {
					CartItem cartItem = new CartItem();
					cartItem.setCart(newCart);
					cartItem.setCartItemNumber(1);
					cartItem.setDateCreated(LocalDateTime.now());
					cartItem.setProduct(product);
					cartItem.setProductQuantity(1);

					if (cartItemService.addItem(cartItem) != null) {
						newCart.setOrderQuantity(1);
						return newCart;
					} else {
						throw new CartErrorException("Item could not be added to cart. Please try again");
					}
				} else {
					throw new CartErrorException("Item could not be added to cart. Please try again");
				}
			}

		} else {
			throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
		}
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
