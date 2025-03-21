package com.example.backend.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private double price;
    private int stock;
    private String image;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
