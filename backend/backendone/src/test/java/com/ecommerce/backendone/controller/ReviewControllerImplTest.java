package com.ecommerce.backendone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.backendone.controller.impl.ReviewControllerImpl;
import com.ecommerce.backendone.dto.ReviewDto;
import com.ecommerce.backendone.dto.mapper.ReviewMapper;
import com.ecommerce.backendone.entity.Review;
import com.ecommerce.backendone.payload.User;
import com.ecommerce.backendone.service.FetchService;
import com.ecommerce.backendone.service.ReviewService;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerImplTest {
	@Mock
	private ReviewService reviewService;
	@Mock
	private FetchService fetchService;
	@InjectMocks
	private ReviewControllerImpl underTest;
	
	@Test
	void addReview_ShouldAddReview_UponValidInput() {
		Integer userId = 1;
		String token = "token";
		User user = User.builder().userId(userId).build();
		ReviewDto reviewDto = ReviewDto.builder().build();
		Review review = ReviewMapper.mapToEntity(reviewDto);
		Review savedReview = Review.builder().reviewId(1).build();
		ReviewDto savedReviewDto = ReviewMapper.mapToDto(savedReview);
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(reviewService.addReview(review, user)).thenReturn(savedReview);
		
		ResponseEntity<ReviewDto> response = underTest.addReview(token, reviewDto);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(savedReviewDto, response.getBody());
	}
	
	@Test
	void deleteReview_ShouldDeleteReview_UponValidInput() {
		String token = "token";
		Integer reviewId = 1;
		User user = User.builder().build();
		ReviewDto reviewDto = ReviewDto.builder().reviewId(reviewId).build();
		Review review = ReviewMapper.mapToEntity(reviewDto);
		
		when(fetchService.getUser(token)).thenReturn(user);
		
		ResponseEntity<Void> response = underTest.deleteReview(token, reviewDto);
		
		verify(reviewService, times(1)).deleteReview(review, user);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
