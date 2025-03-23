package com.ecommerce.backendone.dto.mapper;

import com.ecommerce.backendone.dto.SectionDto;
import com.ecommerce.backendone.entity.Section;

public class SectionMapper {
    public static SectionDto mapToDto(Section entity) {
        return SectionDto.builder()
                .sectionId(entity.getSectionId())
                .sectionName(entity.getSectionName())
                .categories(entity.getCategories().stream()
                        .map(CategoryMapper::mapToDtoExcludeRelations)
                        .toList())
                .build();
    }

    public static SectionDto mapToDtoExcludeRelations(Section entity) {
        return SectionDto.builder()
                .sectionId(entity.getSectionId())
                .sectionName(entity.getSectionName())
                .build();
    }
}
