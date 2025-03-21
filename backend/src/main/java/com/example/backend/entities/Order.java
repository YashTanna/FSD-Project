package com.example.backend.entities;

import java.time.LocalDate;
import java.util.List;

import com.example.backend.dto.Address;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private double totalAmount;
    private String status; // 'pending', 'processing', 'shipped', 'delivered'

    @Embedded
    private Address shippingAddress;

    private String paymentMethod;
    private String paymentStatus; // 'pending', 'paid', 'failed'

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
