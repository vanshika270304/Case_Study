package com.agriculture.user_service.service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document
@Data
@Getter
@Setter
public class Dealer {
    @Id
    private String id; // Use UUID for unique identifier

//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @DBRef
    private User user;

    private String address; // Additional details specific to dealer

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString(); // Generate unique UUID before saving
        }
    }

    // Getters and Setters
}

