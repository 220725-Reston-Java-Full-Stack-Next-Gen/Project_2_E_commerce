package com.revature.services;

import com.revature.models.OrderStatus;
import com.revature.repos.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{
    @Autowired
    private OrderStatusRepo orderStatusRepo;

    @Override
    public OrderStatus addOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepo.save(orderStatus);
    }

    @Override
    public OrderStatus getOrderStatusById(int orderStatusId) {
        return orderStatusRepo.getOrderStatusById(orderStatusId);
    }

    @Override
    public OrderStatus getOrderStatusByOrderStatus(String status) {
        return orderStatusRepo.getOrderStatusByOrderStatus(status);
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepo.updateOrderStatus(orderStatus.getOrderStatus(), orderStatus.getOrderStatusID());
    }

    @Override
    public void deleteOrderStatus(OrderStatus orderStatus) {
        orderStatusRepo.delete(orderStatus);
    }
}
