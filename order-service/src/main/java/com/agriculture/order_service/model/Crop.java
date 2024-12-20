package com.agriculture.order_service.model;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Crop {

	private String id;
    private String farmerId;
    private String name;
    private int quantity;
    private double pricePerUnit;
    private String soilType;
    private LocalDate dateOfHarvest;
    private String status = "Available";
}
