package com.ecommerce.backendone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.backendone.controller.impl.CategoryControllerImpl;
import com.ecommerce.backendone.dto.CategoryDto;
import com.ecommerce.backendone.dto.mapper.CategoryMapper;
import com.ecommerce.backendone.entity.Category;
import com.ecommerce.backendone.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerImplTest {
	@Mock
	private CategoryService categoryService;
	@InjectMocks
	private CategoryControllerImpl underTest;
	
	@Test
	void getCategory_ShouldReturnCategoryDto_UponValidInput() {
		Integer categoryId = 1;
		Category category = Category.builder().categoryId(categoryId).products(Collections.emptyList()).build();
		
		CategoryDto expectedCategoryDto = CategoryMapper.mapToDto(category);
		
		when(categoryService.getCategoryById(categoryId)).thenReturn(category);
		
		ResponseEntity<CategoryDto> response = underTest.getCategory(categoryId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedCategoryDto, response.getBody());
	}

}
