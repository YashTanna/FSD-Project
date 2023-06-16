package com.ecommerce.backendone.dto.mapper;

import com.ecommerce.backendone.dto.ReviewDto;
import com.ecommerce.backendone.entity.Review;

public class ReviewMapper {
    public static ReviewDto mapToDto(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUserId())
                .userName(review.getUserName())
                .rating(review.getRating())
                .text(review.getText())
                .createdAt(review.getCreatedAt())
                .product(review.getProduct())
                .build();
    }
    
    public static Review mapToEntity(ReviewDto reviewDto) {
    	return Review.builder()
    			.reviewId(reviewDto.getReviewId())
    			.userId(reviewDto.getUserId())
    			.userName(reviewDto.getUserName())
                .rating(reviewDto.getRating())
                .text(reviewDto.getText())
                .createdAt(reviewDto.getCreatedAt())
                .product(reviewDto.getProduct())
                .build();
    }
}
