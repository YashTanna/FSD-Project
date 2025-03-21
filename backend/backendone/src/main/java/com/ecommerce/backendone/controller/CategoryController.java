package com.ecommerce.backendone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backendone.dto.CategoryDto;

import jakarta.validation.constraints.NotNull;

@RequestMapping("/categories")
@Validated
public interface CategoryController {
	
	@GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> getCategory(@PathVariable @NotNull Integer categoryId);

}
