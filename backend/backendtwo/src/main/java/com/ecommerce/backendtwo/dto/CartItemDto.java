package com.ecommerce.backendtwo.dto;

import com.ecommerce.backendtwo.payload.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
	@NotNull
    private Integer cartItemId;
    private Integer productId;
    @NotNull
    @Min(1)
    private Integer quantity;
    private Integer userId;
    private Product product;
}
