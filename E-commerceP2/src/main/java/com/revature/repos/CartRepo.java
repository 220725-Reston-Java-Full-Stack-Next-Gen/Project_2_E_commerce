package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Cart;
import com.revature.models.User;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	@Query(value = "INSERT INTO cart (cartID) VALUES (DEFAULT)", nativeQuery = true)
	public Cart saveNew(Cart cart);	
	
	@Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
	public List<Cart> getCart(int userID);

	
	@Query(value = "INSERT INTO cart SELECT product_id=?1 FROM product", nativeQuery = true)
	boolean save(int productID);
	
	@Query(value = "DELETE FROM cart WHERE product_id=?1", nativeQuery = true)
  	boolean delete(int productID);

}
