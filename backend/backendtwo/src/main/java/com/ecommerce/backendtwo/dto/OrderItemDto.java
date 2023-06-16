package com.ecommerce.backendtwo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.ecommerce.backendtwo.payload.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Integer orderItemId;
    private Integer totalPrice;
    private Integer quantity;
    private Date orderDate;
    private Product product;
    private Integer userId;
}
