package com.agriculture.order_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agriculture.order_service.model.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByDealerId(String dealerId);
    List<Order> findByFarmerId(String farmerId);
}
