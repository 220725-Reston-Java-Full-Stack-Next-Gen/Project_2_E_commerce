package com.revature.repos;

import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    Order getOrderByOrderID(int orderID);

    @Query(value = "SELECT * FROM orders WHERE order_user_id = ?1", nativeQuery = true)
    List<Order> getOrdersByOrderOwner(int ownerID);

    @Query(value = "SELECT * FROM orders WHERE order_status_id = ?1", nativeQuery = true)
    List<Order> getOrdersByStatus(int statusID);

    @Query(value = "UPDATE orders SET quantity = ?1, total_price = ?2, date_shipped = ?3, date_modified = ?4, order_payment_id = ?5, order_status_id = ?6", nativeQuery = true)
    boolean updateOrder(int quantity, double totalPrice, LocalDateTime dateShipped, LocalDateTime dateModified, int paymentID, int statusID);

}
