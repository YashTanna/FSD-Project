package com.ecommerce.backendtwo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	private Integer productId;
	private String productName;
	private String imageUrl;
	private Integer sellingPrice;
}
