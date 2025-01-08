package com.agriculture.order_service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agriculture.crop_service.model.Crop;
import com.agriculture.order_service.model.Order;
import com.agriculture.order_service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	@Autowired
    private OrderService orderService;
	
	@GetMapping("/crops/available")
	public ResponseEntity<List<Crop>> getAvailableCrops() {
	        return ResponseEntity.ok(orderService.getAllAvailableCrops());
	    }

    // Place Order
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam String dealerId,
                                            @RequestParam String cropId,
                                            @RequestParam Integer quantity) {
        Order order = orderService.placeOrder(dealerId, cropId, quantity);
        return ResponseEntity.ok(order);
    }
    
    //view order using orderid
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    
    //update order status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String orderId, 
            @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // View Orders for Dealer
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<Order>> getOrdersByDealer(@PathVariable String dealerId) {
        return ResponseEntity.ok(orderService.getOrdersByDealer(dealerId));
    }

    // View Orders for Farmer
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Order>> getOrdersByFarmer(@PathVariable String farmerId) {
        return ResponseEntity.ok(orderService.getOrdersForFarmer(farmerId));
    }
}
