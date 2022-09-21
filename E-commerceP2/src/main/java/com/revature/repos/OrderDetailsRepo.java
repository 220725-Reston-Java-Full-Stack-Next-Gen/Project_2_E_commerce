package com.revature.repos;

import com.revature.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

    @Query(value = "SELECT * FROM order_details WHERE order_details_id = ?1", nativeQuery = true)
    OrderDetails getOrderDetailsById(int id);

    @Query(value = "SELECT * FROM order_details WHERE order_id = ?1", nativeQuery = true)
    List<OrderDetails> getOrderDetailsByOrderId(int orderId);

    @Query(value = "UPDATE order_details SET order_item_number = ?1, unit_price = ?2, product_quantity = ?3, date_modified = ?4, order_id = ?5, product_id = ?6", nativeQuery = true)
    boolean updateOrderDetail(int orderItemNumber, double unitPrice, int quantity, LocalDateTime dateModified, int orderId, int productID);

}
