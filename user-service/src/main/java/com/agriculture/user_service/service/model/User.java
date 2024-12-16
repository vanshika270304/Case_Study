package com.agriculture.user_service.service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

//@Entity
@Document
@Data
public class User {
    
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String number;
    private String email;
    private String password;
    private String role;
}
