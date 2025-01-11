package com.agriculture.payment_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriculture.payment_service.dto.PaymentRequest;
import com.agriculture.payment_service.model.Payment;
import com.agriculture.payment_service.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Place a Payment
    @PostMapping("/place")
    public ResponseEntity<Payment> placePayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.placePayment(paymentRequest);
        return ResponseEntity.ok(payment);
    }

    // Fetch Payment Status by Order ID
    @GetMapping("/{orderId}/status")
    public ResponseEntity<String> getPaymentStatusByOrderId(@PathVariable String orderId) {
        String status = paymentService.getPaymentStatus(orderId);
        return ResponseEntity.ok(status);
    }

    // Fetch Payment History by Dealer ID
    @GetMapping("/dealer/{dealerId}/history")
    public ResponseEntity<List<Payment>> getPaymentHistoryByDealerId(@PathVariable String dealerId) {
        List<Payment> paymentHistory = paymentService.getPaymentHistory(dealerId);
        return ResponseEntity.ok(paymentHistory);
    }
}
