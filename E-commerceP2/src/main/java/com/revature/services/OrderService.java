package com.revature.services;

import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.User;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    Order getOrderById(int orderID);
    List<Order> getOrdersByOrderOwner(User user);
    List<Order> getOrdersByStatus(OrderStatus status);

    boolean updateOrder(Order order);

    void deleteOrder(Order order);
}
