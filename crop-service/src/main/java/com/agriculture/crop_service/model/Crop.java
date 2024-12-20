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

    @NotBlank(message = "Crop name is required")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @DecimalMin(value = "0.1", message = "Price per unit must be greater than 0")
    private double pricePerUnit;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    @FutureOrPresent(message = "Date of harvest cannot be in the past")
    private LocalDate dateOfHarvest;

    private String status = "Available"; // Default status
}
