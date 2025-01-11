package com.agriculture.crop_service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@Document
@Getter
@Setter
public class Crop {
    @Id
    private String id;

    @NotBlank(message = "Farmer ID is required")
    private String farmerId;
    private String name;
    private int quantity;
    private int reservedQuantity = 0;
    private double pricePerUnit;
    private String soilType;
    private LocalDate dateOfHarvest;
    private String status = "Available"; // Default status
}
