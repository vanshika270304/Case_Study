package com.agriculture.payment_service.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Order {

	private String orderId;
    private String dealerId;
    private String farmerId;
    private String cropId;
    private Integer quantity;
    private Double totalPrice;
    private String orderStatus; // "Pending", "Shipped", "Delivered"
    private LocalDateTime orderDate;
}
