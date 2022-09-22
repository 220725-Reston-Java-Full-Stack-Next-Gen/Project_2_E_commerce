package com.revature.services;

import com.revature.models.Payment;

public interface PaymentService {
	
	boolean addPayment(Payment payment);
	
	int updatePayment(Payment payment);
	
	boolean deletePayment(Payment payment);
}
