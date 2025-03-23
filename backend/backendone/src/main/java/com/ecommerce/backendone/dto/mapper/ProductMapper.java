package com.ecommerce.backendone.dto.mapper;

import com.ecommerce.backendone.dto.ProductDto;
import com.ecommerce.backendone.entity.Product;

public class ProductMapper {
    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .markedPrice(product.getMarkedPrice())
                .sellingPrice(product.getSellingPrice())
                .reviewCount(product.getReviewCount())
                .totalRating(product.getTotalRating())
                .reviews(product.getReviews().stream()
                        .map(ReviewMapper::mapToDto)
                        .toList())
                .build();
    }
}
