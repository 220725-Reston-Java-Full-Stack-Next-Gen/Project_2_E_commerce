package com.revature.services;

import com.revature.models.Order;
import com.revature.models.OrderDetails;
import com.revature.repos.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    public OrderDetails addOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepo.save(orderDetails);
    }

    @Override
    public OrderDetails getOrderDetailsById(int id) {
        return orderDetailsRepo.getOrderDetailsById(id);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrder(Order order) {
        return orderDetailsRepo.getOrderDetailsByOrderId(order.getOrderID());
    }

    @Override
    public boolean updateOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepo.updateOrderDetail(orderDetails.getOrderItemNumber(),
                orderDetails.getUnitPrice(), orderDetails.getProductQuantity(),
                orderDetails.getDateModified(), orderDetails.getOrder().getOrderID(),
                orderDetails.getProduct().getProductID());
    }

    @Override
    public void deleteOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepo.delete(orderDetails);
    }
}
