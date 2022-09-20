package com.revature.services;

import com.revature.models.Order;
import com.revature.models.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    OrderDetails addOrderDetails(OrderDetails orderDetails);

    OrderDetails getOrderDetailsById(int id);

    List<OrderDetails> getOrderDetailsByOrder(Order order);

    boolean updateOrderDetails(OrderDetails orderDetails);

    void deleteOrderDetails(OrderDetails orderDetails);
}
