package com.ecommerce.backendone.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendone.controller.CategoryController;
import com.ecommerce.backendone.dto.CategoryDto;
import com.ecommerce.backendone.dto.mapper.CategoryMapper;
import com.ecommerce.backendone.entity.Category;
import com.ecommerce.backendone.service.CategoryService;

@RestController
public class CategoryControllerImpl implements CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<CategoryDto> getCategory(Integer categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(CategoryMapper.mapToDto(category), HttpStatus.OK);
	}
}