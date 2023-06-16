package com.ecommerce.backendone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDto {
	private Integer sectionId;
    private String sectionName;
    private List<CategoryDto> categories;
}
