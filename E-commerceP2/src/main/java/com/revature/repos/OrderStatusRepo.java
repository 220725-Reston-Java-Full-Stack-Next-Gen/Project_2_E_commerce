package com.revature.repos;

import com.revature.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer> {

    @Query(value = "SELECT * FROM order_status WHERE order_status_id = ?1", nativeQuery = true)
    OrderStatus getOrderStatusById(int orderStatusID);

    @Query(value = "SELECT * FROM order_status WHERE order_status = ?1", nativeQuery = true)
    OrderStatus getOrderStatusByOrderStatus(String orderStatus);

    @Query(value = "UPDATE order_status SET order_status = ?1 WHERE order_status_id = ?2", nativeQuery = true)
    boolean updateOrderStatus(String status, int id);
}
