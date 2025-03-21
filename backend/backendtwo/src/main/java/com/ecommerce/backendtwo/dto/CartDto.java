package com.ecommerce.backendtwo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
	private List<CartItemDto> cartItems;
	private Integer totalAmount;

}
