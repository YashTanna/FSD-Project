package com.ecommerce.backendtwo.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.backendtwo.dto.CartDto;
import com.ecommerce.backendtwo.entity.CartItem;

public class CartMapper {
    public static CartDto mapToDto(List<CartItem> cartItems) {
        int totalAmount = cartItems.stream()
                .mapToInt(x -> x.getProduct().getSellingPrice() * x.getQuantity())
                .sum();

        return CartDto.builder()
                .cartItems(cartItems.stream()
                        .map(CartItemMapper::mapToDto)
                        .collect(Collectors.toList()))
                .totalAmount(totalAmount)
                .build();
    }
}
