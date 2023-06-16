package com.ecommerce.backendone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendone.entity.Category;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.CategoryRepository;
import com.ecommerce.backendone.service.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
	@Mock
	private CategoryRepository categoryRepository;
	@InjectMocks
	private CategoryServiceImpl underTest;
	
	@Test
	void getCategoryById_ShouldReturnCategory_UponValidId() {
		Category expectedCategory = Category.builder()
				.categoryId(1)
				.categoryName("Audio")
				.imageUrl("url")
				.build();
		
		when(categoryRepository.findById(1)).thenReturn(Optional.of(expectedCategory));
		
		Category actualCategory = underTest.getCategoryById(1);
		
		assertEquals(expectedCategory, actualCategory);
	}
	
	@Test
	void getCategoryById_ShouldThrowResourceNotFoundException_UponInvalidId() {
		when(categoryRepository.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> underTest.getCategoryById(1));
	}

}
