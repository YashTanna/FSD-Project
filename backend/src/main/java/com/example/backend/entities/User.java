package com.example.backend.entities;

import java.time.LocalDate;
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
    private String password; // Hashed
    private String role; // 'user' or 'admin'

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
