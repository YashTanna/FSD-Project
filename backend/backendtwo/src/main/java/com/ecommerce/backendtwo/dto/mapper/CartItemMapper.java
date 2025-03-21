package com.ecommerce.backendtwo.dto.mapper;

import com.ecommerce.backendtwo.dto.CartItemDto;
import com.ecommerce.backendtwo.entity.CartItem;

public class CartItemMapper {
    public static CartItemDto mapToDto(CartItem entity) {
        return CartItemDto.builder()
                .cartItemId(entity.getCartItemId())
                .quantity(entity.getQuantity())
                .userId(entity.getUserId())
                .productId(entity.getProductId())
                .product(entity.getProduct())
                .build();
    }

    public static CartItem mapToEntity(CartItemDto dto) {
        return CartItem.builder()
                .cartItemId(dto.getCartItemId())
                .quantity(dto.getQuantity())
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .product(dto.getProduct())
                .build();
    }
}
