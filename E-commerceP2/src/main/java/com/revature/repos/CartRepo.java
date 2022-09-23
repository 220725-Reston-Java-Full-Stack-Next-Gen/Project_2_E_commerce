package com.revature.repos;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
>>>>>>> Raphael
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Cart;
import com.revature.models.User;
<<<<<<< HEAD

import java.util.List;

=======
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
>>>>>>> Raphael
public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	@Query(value = "INSERT INTO cart (cartID) VALUES (DEFAULT)", nativeQuery = true)
	public Cart saveNew(Cart cart);	
	
	@Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
<<<<<<< HEAD
	public List<Cart> getCart(int userID);
	
	@Query(value = "SELECT * FROM cart JOINS cart_items ON cart.cart_id = cart_items.cart_id WHERE cart_id=?1", nativeQuery = true)
	public List<Cart> getCartItems(int cart_id);

	
	@Query(value = "INSERT INTO cart SELECT product_id=?1 FROM product", nativeQuery = true)
	boolean save(int productID);
	
=======
	public Cart getCart(int userID);

	@Query(value = "SELECT * FROM cart where cart_id = ?1", nativeQuery = true)
	Cart getCartByCartID(int cartID);

	@Query(value = "INSERT INTO cart SELECT product_id=?1 FROM product", nativeQuery = true)
	boolean save(int productID);

	@Modifying
	@Query(value = "UPDATE cart SET date_modified = ?1, order_quantity = ?2 WHERE user_id = ?3", nativeQuery = true)
	int updateCart(LocalDateTime dateModified, int totalQuantity, int cartId);

>>>>>>> Raphael
	@Query(value = "DELETE FROM cart WHERE product_id=?1", nativeQuery = true)
  	boolean delete(int productID);

}
