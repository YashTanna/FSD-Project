package com.ecommerce.backendone.dto;

import com.ecommerce.backendone.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer productId;
    private String productName;
    private String imageUrl;
    private String description;
    private Integer markedPrice;
    private Integer sellingPrice;
    private Integer reviewCount;
    private Integer totalRating;
    private Category category;
    private List<ReviewDto> reviews;
}
