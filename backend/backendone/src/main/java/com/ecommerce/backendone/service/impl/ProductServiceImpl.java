package com.ecommerce.backendone.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.ProductRepository;
import com.ecommerce.backendone.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " not found"));
    }
    
    @Override
    public List<Product> getProductsByIds(List<Integer> ids) {
    	return productRepository.findAllById(ids);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public List<Product> getProductsWithText(String text) {
        Product[] products = productRepository.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(text, text);
        return Arrays.asList(products);
    }

}
