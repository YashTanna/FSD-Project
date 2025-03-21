package com.ecommerce.backendtwo.dto.mapper;

import com.ecommerce.backendtwo.dto.OrderItemDto;
import com.ecommerce.backendtwo.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDto mapToDto(OrderItem entity) {
        return OrderItemDto.builder()
                .orderItemId(entity.getOrderItemId())
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .orderDate(entity.getOrderDate())
                .product(entity.getProduct())
                .userId(entity.getUserId())
                .build();
    }
}
