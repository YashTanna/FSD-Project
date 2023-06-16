package com.ecommerce.backendtwo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backendtwo.dto.CartDto;
import com.ecommerce.backendtwo.dto.CartItemDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RequestMapping("/cart")
@Validated
public interface CartController {
    @GetMapping("/")
    ResponseEntity<CartDto> getCart(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token);

    @PostMapping("/add-item")
    ResponseEntity<Void> addItemToCart(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody @NotNull Integer productId);

    @DeleteMapping("/remove-item")
    ResponseEntity<CartDto> removeItemFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody @NotNull Integer cartItemId);

    @PutMapping("/update-item")
    ResponseEntity<CartDto> updateItemInCart(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody CartItemDto cartItemDto);

    @PostMapping("/get-item")
    ResponseEntity<CartItemDto> getItemFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody @NotNull Integer productId);
}
