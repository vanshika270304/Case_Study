package com.agriculture.payment_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agriculture.payment_service.dto.PaymentRequest;
import com.agriculture.payment_service.feign.OrderFeignClient;
import com.agriculture.payment_service.model.Order;
import com.agriculture.payment_service.model.Payment;
import com.agriculture.payment_service.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderFeignClient orderFeignClient;

    public PaymentService(PaymentRepository paymentRepository, OrderFeignClient orderFeignClient) {
        this.paymentRepository = paymentRepository;
        this.orderFeignClient = orderFeignClient;
    }

    // Place a Payment
    public Payment placePayment(PaymentRequest paymentRequest) {
        // Fetch order details from Order Service
        Order order = (Order) orderFeignClient.getOrderById(paymentRequest.getOrderId());

        // Check if the payment amount matches the total price
        if (order.getTotalPrice() != paymentRequest.getAmount())
        
        {
        	throw new IllegalArgumentException("Payment amount does not match the total price.");
        }
        
        // Create and save the payment
        Payment payment = new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setDealerId(order.getDealerId());
        payment.setFarmerId(order.getFarmerId());
        payment.setCropId(order.getCropId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setTimestamp(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        // If payment is successful, update the order status and crop quantities
        if ("Success".equalsIgnoreCase(paymentRequest.getPaymentStatus())) {
            // Update order status to "Confirmed" in Order Service
            orderFeignClient.updateOrderStatus(order.getOrderId(), "Confirmed");
        }
        return savedPayment;
    }

    // Fetch Payment Status by Order ID
    public String getPaymentStatus(String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for Order ID: " + orderId));
        return payment.getPaymentStatus();
    }

    // Fetch Payment History by Dealer ID
    public List<Payment> getPaymentHistory(String dealerId) {
        return paymentRepository.findByDealerId(dealerId);
    }
    
}

