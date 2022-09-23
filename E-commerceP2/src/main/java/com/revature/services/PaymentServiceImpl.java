package com.revature.services;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Payment;
import com.revature.repos.PaymentRepo;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	public PaymentServiceImpl(PaymentRepo paymentRepo) {
		this.paymentRepo = paymentRepo;
	}
	
	
	@Override
	public boolean addPayment(Payment payment) {
		// TODO Auto-generated method stub
		int pId = paymentRepo.save(payment).getId();
		return (pId > 0) ? true:false;
	}

	@Override
	public int updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepo.updatePayment(payment.getPaymentType(),payment.getPaymentStatus(), payment.getPaymentNumber(), payment.getPaymentExpDate(), payment.getPaymentCvv(),payment.getPaymentDateLastUpdated() , payment.getId());
	}

	@Override
	public boolean deletePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.delete(payment);
		return true;
	}

=======
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
>>>>>>> Raphael
}
