package com.example.backend.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private String productId;
    private String name;
    private double price;
    private int quantity;
}
