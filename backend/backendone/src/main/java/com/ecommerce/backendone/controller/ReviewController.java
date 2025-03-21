package com.ecommerce.backendone.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backendone.dto.ReviewDto;

import jakarta.validation.constraints.NotBlank;

@RequestMapping("/reviews")
@Validated
public interface ReviewController {
	
	@PostMapping("/")
    public ResponseEntity<ReviewDto> addReview(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody @Validated(ReviewDto.AddReview.class) ReviewDto reviewDto);

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteReview(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token, @RequestBody @Validated(ReviewDto.DeleteReview.class) ReviewDto reviewDto);
}
