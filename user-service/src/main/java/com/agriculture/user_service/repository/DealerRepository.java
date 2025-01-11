package com.agriculture.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agriculture.user_service.service.model.Dealer;

//public interface DealerRepository extends JpaRepository<Dealer, Long> {
//}

public interface DealerRepository extends MongoRepository<Dealer, String> {
	Dealer findByUserId(String userId);
}