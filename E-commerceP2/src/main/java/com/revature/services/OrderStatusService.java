package com.revature.services;

import com.revature.models.OrderStatus;

public interface OrderStatusService {
    OrderStatus addOrderStatus(OrderStatus orderStatus);
    OrderStatus getOrderStatusById(int orderStatusId);
    OrderStatus getOrderStatusByOrderStatus(String status);
    boolean updateOrderStatus(OrderStatus orderStatus);
    void deleteOrderStatus(OrderStatus orderStatus);
}
