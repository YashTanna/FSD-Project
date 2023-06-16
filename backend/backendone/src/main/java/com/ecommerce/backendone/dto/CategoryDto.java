package com.ecommerce.backendone.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
	private Integer categoryId;
    private String categoryName;
    private String imageUrl;
    private List<ProductDto> products;
    private SectionDto section;
    
}
