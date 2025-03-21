package com.ecommerce.backendtwo.service;

import java.util.List;

import com.ecommerce.backendtwo.payload.Product;
import com.ecommerce.backendtwo.payload.User;

public interface FetchService {
	List<Product> getProductsById(List<Integer> ids);

	User getUser(String token);

}
