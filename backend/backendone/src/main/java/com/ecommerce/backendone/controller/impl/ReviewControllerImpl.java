package com.ecommerce.backendone.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendone.controller.ReviewController;
import com.ecommerce.backendone.dto.ReviewDto;
import com.ecommerce.backendone.dto.mapper.ReviewMapper;
import com.ecommerce.backendone.entity.Review;
import com.ecommerce.backendone.payload.User;
import com.ecommerce.backendone.service.FetchService;
import com.ecommerce.backendone.service.ReviewService;

@RestController
public class ReviewControllerImpl implements ReviewController {
	@Autowired
    private FetchService fetchService;
	@Autowired
    private ReviewService reviewService;

    @Override
    public ResponseEntity<ReviewDto> addReview(String token, ReviewDto reviewDto) {
    	Review review = ReviewMapper.mapToEntity(reviewDto);
        User user = fetchService.getUser(token);
        Review savedReview = reviewService.addReview(review, user);
        return new ResponseEntity<>(ReviewMapper.mapToDto(savedReview), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteReview(String token, ReviewDto reviewDto) {
    	Review review = ReviewMapper.mapToEntity(reviewDto);
        User user = fetchService.getUser(token);
        reviewService.deleteReview(review, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
