package com.example.backend.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password; // Stored as an encoded (hashed) value
    private String role; // 'user' or 'admin'

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
