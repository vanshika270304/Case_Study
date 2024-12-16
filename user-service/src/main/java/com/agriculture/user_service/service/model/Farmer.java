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
public class Farmer {
    @Id
    private String id;

//    @OneToOne
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @DBRef
    private User user;

    private String address; 

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString(); // Generate unique UUID before saving
        }
    }

}
