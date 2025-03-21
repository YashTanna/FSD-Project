package com.ecommerce.backendone.service;

import java.util.List;

import com.ecommerce.backendone.entity.Product;

public interface ProductService {
	
	Product getProductById(Integer productId);
	
	List<Product> getProductsByIds(List<Integer> ids);
	
    Product updateProduct(Product product);

	List<Product> getProductsWithText(String text);

}
