package com.ecommerce.backendone.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.backendone.dto.ProductDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RequestMapping("/products")
@Validated
public interface ProductController {
	
	@GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable @NotNull Integer productId);

    @PostMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestBody @NotNull List<Integer> ids);
    
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getProductsWithTextLike(@RequestParam @NotBlank String text);

}
