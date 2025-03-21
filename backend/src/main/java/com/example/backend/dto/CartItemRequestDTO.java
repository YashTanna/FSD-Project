package com.example.backend.dto;

import lombok.Data;

@Data
public class CartItemRequestDTO {
    private String productId;
    private int quantity;
}
