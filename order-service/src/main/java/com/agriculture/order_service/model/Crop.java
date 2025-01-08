package com.agriculture.order_service.model;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Crop {

	private String id;
    private String farmerId;
    private String name;
    private int quantity;
    private int reservedQuantity;
    private double pricePerUnit;
    private String soilType;
    private LocalDate dateOfHarvest;
    private String status = "Available";
}
