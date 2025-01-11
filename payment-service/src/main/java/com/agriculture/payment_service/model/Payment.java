package com.agriculture.payment_service.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Document
public class Payment {
	
	@Id
    private String id;
    private String orderId;
    private String dealerId;
    private String farmerId;
    private String cropId;
    private double amount;
    private String paymentStatus; // e.g., Pending, Success, Failed
    private String paymentMethod; // e.g., Card, UPI, NetBanking
    private LocalDateTime timestamp;
}
