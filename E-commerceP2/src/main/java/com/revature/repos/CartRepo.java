package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Cart;
import com.revature.models.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	@Query(value = "INSERT INTO cart (cartID) VALUES (DEFAULT)", nativeQuery = true)
	public Cart saveNew(Cart cart);	
	
	@Query(value = "SELECT * FROM cart WHERE userID = ?1", nativeQuery = true)
	public Cart getCart(User user);	
	
	@Query(value = "INSERT INTO cart SELECT product_id=?1 FROM product", nativeQuery = true)
	boolean save(int productID);
	
	@Query(value = "DELETE FROM cart WHERE product_id=?1", nativeQuery = true)
  	boolean delete(int productID);

}
