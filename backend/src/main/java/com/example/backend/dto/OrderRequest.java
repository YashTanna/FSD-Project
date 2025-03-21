package com.example.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItemRequest> items;
    private double totalAmount;
    private Address shippingAddress;
    private String paymentMethod;
    private String paymentStatus; // 'pending', 'paid', 'failed'
}
