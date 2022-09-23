package com.revature.services;

import com.revature.models.Payment;
<<<<<<< HEAD

public interface PaymentService {
	
	boolean addPayment(Payment payment);
	
	int updatePayment(Payment payment);
	
	boolean deletePayment(Payment payment);
=======
import com.revature.models.User;

import java.util.List;

public interface PaymentService {
    Payment addPayment(Payment payment);

    Payment getPaymentById(int id);

    List<Payment> getPreviousPayments(User user);

    void deletePayment(Payment payment);
>>>>>>> Raphael
}
