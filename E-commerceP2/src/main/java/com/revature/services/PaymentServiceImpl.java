package com.revature.services;

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

}
