package com.example.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Product;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product p) {
        Product createProduct = productRepository.save(p);
        return createProduct;
    }

    public Product updateProduct(Product p, String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        p.setId(product.getId());
        return productRepository.save(p);
    }

    public Map<String, String> deleteProduct(String id) {
        productRepository.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", String.format("Product with id %s deleted successfully...", id));

        return response;
    }

}
