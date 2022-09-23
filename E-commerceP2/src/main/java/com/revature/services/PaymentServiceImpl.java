package com.revature.services;

import com.revature.controllers.PaymentErrorException;
import com.revature.models.*;
import com.revature.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;


    @Override
    public Payment addPayment(Payment payment) {

        return paymentRepo.save(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepo.getPaymentById(id);
    }

    @Override
    public List<Payment> getPreviousPayments(User user) {
        return paymentRepo.getPreviousPayment(user.getId());
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentRepo.delete(payment);
    }
}
