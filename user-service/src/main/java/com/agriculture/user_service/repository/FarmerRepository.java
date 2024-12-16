package com.agriculture.user_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agriculture.user_service.service.model.Farmer;

//public interface FarmerRepository extends JpaRepository<Farmer, Long> {
//}

public interface FarmerRepository extends MongoRepository<Farmer, String> {
}