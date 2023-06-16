package com.ecommerce.backendone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendone.entity.Category;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.CategoryRepository;
import com.ecommerce.backendone.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + categoryId + " not found"));
    }
}
