package com.ecommerce.backendone.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendone.controller.ProductController;
import com.ecommerce.backendone.dto.ProductDto;
import com.ecommerce.backendone.dto.mapper.ProductMapper;
import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.service.ProductService;

@RestController
public class ProductControllerImpl implements ProductController {
	@Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductDto> getProduct(Integer productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(ProductMapper.mapToDto(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProducts(List<Integer> productIds) {
        List<Product> products = productService.getProductsByIds(productIds);
        List<ProductDto> productDtos = products.stream()
        		.map(ProductMapper::mapToDto)
        		.toList();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<ProductDto>> getProductsWithTextLike(String text) {
		List<Product> products = productService.getProductsWithText(text);
		List<ProductDto> productDtos = products.stream()
				.map(ProductMapper::mapToDto)
				.toList();
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}

}