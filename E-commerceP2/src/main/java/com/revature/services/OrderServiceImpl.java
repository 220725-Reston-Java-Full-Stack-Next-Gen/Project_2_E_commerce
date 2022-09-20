package com.revature.services;

import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.User;
import com.revature.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order getOrderById(int orderID) {
        return orderRepo.getOrderByOrderID(orderID);
    }

    @Override
    public List<Order> getOrdersByOrderOwner(User user) {
        return orderRepo.getOrdersByOrderOwner(user.getId());
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrdersByStatus(status.getOrderStatusID());
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderRepo.updateOrder(order.getOrderQuantity(), order.getOrderTotalPrice(),
                order.getDateShipped(), order.getDateModified(), order.getPayment().getId(),
                order.getOrderStatus().getOrderStatusID());
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepo.delete(order);
    }
}
