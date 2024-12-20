package com.agriculture.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Getter
@Setter
public class Order {
    @Id
    private String orderId;
    private String dealerId;
    private String farmerId;
    private String cropId;
    private Integer quantity;
    private Double totalPrice;
    private String orderStatus; // "Pending", "Shipped", "Delivered"
    private LocalDateTime orderDate;
}
