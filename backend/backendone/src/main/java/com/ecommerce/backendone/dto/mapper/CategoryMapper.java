package com.ecommerce.backendone.dto.mapper;

import com.ecommerce.backendone.dto.CategoryDto;
import com.ecommerce.backendone.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToDto(Category entity) {
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .imageUrl(entity.getImageUrl())
                .products(entity.getProducts().stream()
                        .map(ProductMapper::mapToDto)
                        .toList())
                .build();
    }

    public static CategoryDto mapToDtoExcludeRelations(Category entity) {
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
