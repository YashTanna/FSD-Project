package com.ecommerce.backendtwo.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backendtwo.dto.OrderItemDto;

import jakarta.validation.constraints.NotBlank;

@RequestMapping("/orders")
public interface OrderController {
    @GetMapping("/")
    ResponseEntity<List<OrderItemDto>> getOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token);

    @PostMapping("/add-orders")
    ResponseEntity<Void> addOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token);
}
