package com.revature.repos;

import com.revature.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    @Query(value = "SELECT * FROM payment WHERE payment_id = ?1", nativeQuery = true)
    Payment getPaymentById(int id);

    @Query(value = "SELECT * FROM payment WHERE payment_user_id = ?1", nativeQuery = true)
    List<Payment> getPreviousPayment(int userId);
}
