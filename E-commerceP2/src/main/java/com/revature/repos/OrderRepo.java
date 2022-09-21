package com.revature.repos;

import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional()
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    Order getOrderByOrderID(int orderID);

    @Query(value = "SELECT * FROM orders WHERE order_user_id = ?1", nativeQuery = true)
    List<Order> getOrdersByOrderOwner(int ownerID);

    @Query(value = "SELECT * FROM orders WHERE order_status_id = ?1", nativeQuery = true)
    List<Order> getOrdersByStatus(int statusID);

    @Modifying
    @Query(value = "UPDATE orders SET date_shipped = ?1, date_delivered = ?2, date_modified = ?3, order_status_id = ?4 WHERE order_id =?5", nativeQuery = true)
    int updateOrder(LocalDateTime dateShipped, LocalDateTime dateDelivered, LocalDateTime dateModified, int statusID, int orderID);

}
