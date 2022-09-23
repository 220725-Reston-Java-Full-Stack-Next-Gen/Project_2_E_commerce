package com.revature.repos;

import com.revature.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    @Query(value = "SELECT * FROM cart_items WHERE cart_item_id = ?1", nativeQuery = true)
    CartItem getCartItem(int id);

    @Query(value = "SELECT * FROM cart_items WHERE cart_id = ?1", nativeQuery = true)
    List<CartItem> getCartItems(int cartId);

    @Modifying
    @Query(value = "UPDATE cart_items SET date_modified = ?1, product_quantity = ?2 WHERE cart_item_id = ?3", nativeQuery = true)
    int updateItem(LocalDateTime dateModified, int quantity, int cartItemId);

}
