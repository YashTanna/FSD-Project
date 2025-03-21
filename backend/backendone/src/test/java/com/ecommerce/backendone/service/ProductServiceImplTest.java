package com.ecommerce.backendone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.ProductRepository;
import com.ecommerce.backendone.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private ProductServiceImpl underTest;

	@Test
	void getProductById_ShouldReturnProduct_UponValidId() {
		Integer productId = 1;
		Product expectedProduct = Product.builder().productId(productId).build();

		when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

		Product actualProduct = underTest.getProductById(productId);

		assertEquals(expectedProduct, actualProduct);
	}

	@Test
	void getProductById_ShouldThrowResourceNotFoundException_UponInvalidId() {
		Integer productId = 1;
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> underTest.getProductById(productId));
	}

	@Test
	void getProductsByIds_ShouldReturnProducts_UponValidIds() {
		List<Integer> ids = List.of(1, 2, 3);
		List<Product> expectedProducts = ids.stream().map(x -> Product.builder().productId(x).build()).toList();

		when(productRepository.findAllById(ids)).thenReturn(expectedProducts);

		List<Product> actualProducts = underTest.getProductsByIds(ids);

		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	void updateProduct_ShouldReturnProduct_UponValidProduct() {
		Integer productId = 1;
		Product product = Product.builder().productId(productId).build();
		Product expectedProduct = Product.builder().productId(productId).build();

		when(productRepository.save(product)).thenReturn(expectedProduct);

		Product actualProduct = underTest.updateProduct(product);

		assertEquals(expectedProduct, actualProduct);
	}
	
	@Test
	void updateProduct_ShouldCallSave_UponValidProduct() {
		Product product = Product.builder().productId(1).build();
		when(productRepository.save(product)).thenReturn(product);
		
		underTest.updateProduct(product);
		
		verify(productRepository, times(1)).save(product);
	}
	
	void getProductsWithText_ShouldReturnProducts_UponMatchingResults() {
		String text = "Something";
		Product[] products = {
				Product.builder().productId(1).build(),
				Product.builder().productId(2).build(),
				Product.builder().productId(3).build()
		};
		
		List<Product> expectedProducts = Arrays.asList(products);
		
		when(productRepository.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(text, text))
		.thenReturn(products);
		
		List<Product> actualProducts = underTest.getProductsWithText(text);
		
		assertEquals(expectedProducts, actualProducts);
	}
}