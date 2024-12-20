package com.agriculture.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.agriculture.crop_service.model.Crop;
import com.agriculture.order_service.feign.CropServiceClient;
import com.agriculture.order_service.model.Order;
import com.agriculture.order_service.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CropServiceClient cropFeignClient;

    // Get all available crops
    public List<Crop> getAllAvailableCrops() {
        return cropFeignClient.getAvailableCrops();
    }

    // Place an order
    public Order placeOrder(String dealerId, String cropId, int quantity) {
        Crop crop = cropFeignClient.getCropById(cropId);
        if (crop.getQuantity() < quantity) {
            throw new RuntimeException("Not enough crop quantity available");
        }

        // Calculate total price
        double totalPrice = crop.getPricePerUnit() * quantity;

        // Create and save order
        Order order = new Order();
        order.setDealerId(dealerId);
        order.setFarmerId(crop.getFarmerId()); // Farmer ID should be retrieved dynamically
        order.setCropId(cropId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus("Pending");
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // Get orders by dealer
    public List<Order> getOrdersByDealer(String dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    // Get orders for farmer
    public List<Order> getOrdersForFarmer(String farmerId) {
        return orderRepository.findByFarmerId(farmerId);
    }
}
