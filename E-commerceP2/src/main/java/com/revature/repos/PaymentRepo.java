package com.revature.repos;

<<<<<<< HEAD
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
=======
import com.revature.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> Raphael
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import com.revature.models.Payment;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
	
	@Modifying
	@Query( value =" update payment set payment_type=?1, payment_status=?2, payment_number=?3, payment_exp_date=?4, payment_Cvv=?5, payment_date_last_updated=?6 where payment_user_id =?7 ",nativeQuery = true)
	public int updatePayment(String paymentType, String paymentStatus, String paymentNumber, LocalDate paymentExpDate,int paymentCvv, LocalDate lastUpdate, int userId);
=======
import java.util.List;

@Transactional
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    @Query(value = "SELECT * FROM payment WHERE payment_id = ?1", nativeQuery = true)
    Payment getPaymentById(int id);

    @Query(value = "SELECT * FROM payment WHERE payment_user_id = ?1", nativeQuery = true)
    List<Payment> getPreviousPayment(int userId);
>>>>>>> Raphael
}
