package com.agriculture.payment_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agriculture.payment_service.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String>{
	Optional<Payment> findByOrderId(String orderId);
    List<Payment> findByDealerId(String dealerId);
}
