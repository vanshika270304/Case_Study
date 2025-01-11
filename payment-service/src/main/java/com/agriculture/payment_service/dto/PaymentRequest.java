package com.agriculture.payment_service.dto;

import lombok.Data;

@Data
public class PaymentRequest {
	private String orderId;
    private double amount;
    private String paymentStatus;
    private String paymentMethod;
}
