package com.example.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Product;
import com.example.backend.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByIdEntity(@PathVariable String id) {
        return new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
    }
    
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct){
        return ResponseEntity.ok(productService.createProduct(newProduct));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product updateProduct,@PathVariable String id){
        return ResponseEntity.ok(productService.updateProduct(updateProduct, id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String,String>> deleteProduct(@PathVariable String id){
        
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
