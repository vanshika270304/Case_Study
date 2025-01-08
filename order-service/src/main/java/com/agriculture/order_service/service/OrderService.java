package com.agriculture.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agriculture.crop_service.model.Crop;
//import com.agriculture.crop_service.repository.CropRepository;
import com.agriculture.order_service.feign.CropServiceClient;
import com.agriculture.order_service.model.Order;
import com.agriculture.order_service.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
//    @Autowired
//    private CropRepository cropRepository;
    @Autowired
    private CropServiceClient cropFeignClient;

    
    // Get all available crops
    public List<Crop> getAllAvailableCrops() {
        return cropFeignClient.getAvailableCrops();
    }
    

    // Place an order
    public Order placeOrder(String dealerId, String cropId, int quantity) {
        Crop crop = cropFeignClient.getCropById(cropId);
        if (crop.getQuantity() - crop.getReservedQuantity() < quantity) {
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
        
        crop.setReservedQuantity(quantity + crop.getReservedQuantity());
//        cropRepository.save(crop);
        cropFeignClient.updateCrop(cropId, crop);
        return orderRepository.save(order);
    }
    
    
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

  
    
    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        // Handle status change logic
        if ("Confirmed".equalsIgnoreCase(status)) {
            // Fetch crop details from Crop Service
            Crop crop = cropFeignClient.getCropById(order.getCropId());
            
            if (crop.getReservedQuantity() < order.getQuantity()) {
                throw new IllegalArgumentException("Insufficient reserved quantity to confirm this order");
            }
            
            crop.setQuantity(crop.getQuantity()- order.getQuantity());
            crop.setReservedQuantity(crop.getReservedQuantity() - order.getQuantity());
            cropFeignClient.updateCrop(order.getCropId(), crop);

            order.setOrderStatus(status);
        } else {
            // Handle other statuses (e.g., Shipped, Delivered)
            order.setOrderStatus(status);
        }
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
