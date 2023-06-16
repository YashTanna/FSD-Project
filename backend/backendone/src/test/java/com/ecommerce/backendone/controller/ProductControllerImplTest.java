package com.ecommerce.backendone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.backendone.controller.impl.ProductControllerImpl;
import com.ecommerce.backendone.dto.ProductDto;
import com.ecommerce.backendone.dto.mapper.ProductMapper;
import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerImplTest {
	@Mock
	private ProductService productService;
	@InjectMocks
	private ProductControllerImpl underTest;
	
	@Test
	void getProduct_ShouldReturnProductDto_UponValidInput() {
		Integer productId = 1;
		Product product = Product.builder().productId(productId).reviews(Collections.emptyList()).build();
		ProductDto expectedProductDto = ProductMapper.mapToDto(product);
		
		when(productService.getProductById(productId)).thenReturn(product);
		
		ResponseEntity<ProductDto> response = underTest.getProduct(productId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedProductDto, response.getBody());
	}
	
	@Test
	void getProducts_ShouldReturnProductDtos_UponValidInput() {
		List<Integer> productIds = List.of(1, 2, 3);
		List<Product> products = productIds.stream()
				.map(x -> Product.builder().productId(x).reviews(Collections.emptyList()).build())
				.toList();
		List<ProductDto> expectedProductDtos = products.stream()
				.map(ProductMapper::mapToDto)
				.toList();
		
		when(productService.getProductsByIds(productIds)).thenReturn(products);
		
		ResponseEntity<List<ProductDto>> response = underTest.getProducts(productIds);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedProductDtos, response.getBody());
		
	}
	
	@Test
	void getProductsWithTextLike_ShouldReturnProductDtos() {
		String text = "Search_String";
		List<Integer> productIds = List.of(1, 2, 3);
		List<Product> products = productIds.stream()
				.map(x -> Product.builder().productId(x).reviews(Collections.emptyList()).build())
				.toList();
		List<ProductDto> expectedProductDtos = products.stream()
				.map(ProductMapper::mapToDto)
				.toList();
		
		when(productService.getProductsWithText(text)).thenReturn(products);
		
		ResponseEntity<List<ProductDto>> response = underTest.getProductsWithTextLike(text);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedProductDtos, response.getBody());
	}

}
