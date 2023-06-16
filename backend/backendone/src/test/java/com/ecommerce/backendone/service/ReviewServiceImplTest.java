package com.ecommerce.backendone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.entity.Review;
import com.ecommerce.backendone.exception.NotAuthorizedException;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.payload.User;
import com.ecommerce.backendone.repository.ReviewRepository;
import com.ecommerce.backendone.service.impl.ReviewServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {
	@Mock
	private ReviewRepository reviewRepository;
	@Mock
	private ProductService productService;
	@InjectMocks
	private ReviewServiceImpl underTest;
	
	@Test
	void addReview_ShouldSaveReview_UponAuthorizedUser() {
		Integer productId = 1;
		Integer productReviewCount = 2;
		Integer productTotalRating = 5;
		Integer reviewRating = 3;
		Integer userId = 4;
		
		Review review = Review.builder()
				.userId(userId)
				.product(Product.builder().productId(productId).build())
				.rating(reviewRating)
				.build();
		Product product = Product.builder()
				.productId(productId)
				.reviewCount(productReviewCount)
				.totalRating(productTotalRating)
				.build();
		User user = User.builder()
				.userId(userId)
				.build();
		
		
		when(reviewRepository.save(review)).thenReturn(review);
		when(productService.getProductById(productId)).thenReturn(product);
		when(productService.updateProduct(product)).thenReturn(product);
		
		underTest.addReview(review, user);
		
		verify(reviewRepository, times(1)).save(review);		
		
	}
	
	@Test
	void addReview_ShouldUpdateProduct_UponAuthorizedUser() {
		Integer productId = 1;
		Integer productReviewCount = 2;
		Integer productTotalRating = 5;
		Integer reviewRating = 3;
		Integer userId = 4;
		
		Review review = Review.builder()
				.userId(userId)
				.product(Product.builder().productId(productId).build())
				.rating(reviewRating)
				.build();
		Product product = Product.builder()
				.productId(productId)
				.reviewCount(productReviewCount)
				.totalRating(productTotalRating)
				.build();
		User user = User.builder()
				.userId(userId)
				.build();
		
		
		when(reviewRepository.save(review)).thenReturn(review);
		when(productService.getProductById(productId)).thenReturn(product);
		when(productService.updateProduct(product)).thenReturn(product);
		
		underTest.addReview(review, user);
		
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(productService, times(1)).updateProduct(productCaptor.capture());
		Product capturedProduct = productCaptor.getValue();
		assertEquals(productReviewCount + 1, capturedProduct.getReviewCount());
		assertEquals(productTotalRating + reviewRating, capturedProduct.getTotalRating());
		
	}
	
	@Test
	void addReview_ShouldThrowNotAuthorizedException_UponUnauthorizedUser() {
		Integer reviewUserId = 4;
		Integer userUserId = 3;
		
		Review review = Review.builder()
				.userId(reviewUserId)
				.build();
		User user = User.builder()
				.userId(userUserId)
				.build();
		
		assertThrows(NotAuthorizedException.class, () -> underTest.addReview(review, user));
				
	}
	
	@Test
	void deleteReview_ShouldDeleteReview_UponAuthorizedUser() {
		Integer productId = 1;
		Integer productReviewCount = 2;
		Integer productTotalRating = 5;
		Integer reviewId = 7;
		Integer reviewRating = 3;
		Integer userId = 4;
		
		Review review = Review.builder()
				.reviewId(reviewId)
				.userId(userId)
				.product(Product.builder().productId(productId).build())
				.rating(reviewRating)
				.build();
		Product product = Product.builder()
				.productId(productId)
				.reviewCount(productReviewCount)
				.totalRating(productTotalRating)
				.build();
		User user = User.builder()
				.userId(userId)
				.build();
		
		when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
		when(productService.getProductById(productId)).thenReturn(product);
		when(productService.updateProduct(product)).thenReturn(product);
		
		underTest.deleteReview(review, user);
		
		verify(reviewRepository, times(1)).deleteById(reviewId);		
		
	}
	
	@Test
	void deleteReview_ShouldUpdateProduct_UponAuthorizedUser() {
		Integer productId = 1;
		Integer productReviewCount = 2;
		Integer productTotalRating = 5;
		Integer reviewId = 7;
		Integer reviewRating = 3;
		Integer userId = 4;
		
		Review review = Review.builder()
				.reviewId(reviewId)
				.userId(userId)
				.product(Product.builder().productId(productId).build())
				.rating(reviewRating)
				.build();
		Product product = Product.builder()
				.productId(productId)
				.reviewCount(productReviewCount)
				.totalRating(productTotalRating)
				.build();
		User user = User.builder()
				.userId(userId)
				.build();
		
		
		when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
		when(productService.getProductById(productId)).thenReturn(product);
		when(productService.updateProduct(product)).thenReturn(product);
		
		underTest.deleteReview(review, user);
		
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(productService, times(1)).updateProduct(productCaptor.capture());
		Product capturedProduct = productCaptor.getValue();
		assertEquals(productReviewCount - 1, capturedProduct.getReviewCount());
		assertEquals(productTotalRating - reviewRating, capturedProduct.getTotalRating());
		
	}
	
	@Test
	void deleteReview_ShouldThrowNotAuthorizedException_UponUnauthorizedUser() {
		Integer reviewUserId = 4;
		Integer userUserId = 3;
		
		Review review = Review.builder()
				.userId(reviewUserId)
				.build();
		User user = User.builder()
				.userId(userUserId)
				.build();
		
		assertThrows(NotAuthorizedException.class, () -> underTest.deleteReview(review, user));
				
	}
	
	@Test
	void getReviewById_ShouldReturnReview_UponValidInput() {
		Integer reviewId = 1;
		Review expectedReview = Review.builder()
				.reviewId(reviewId)
				.build();
		
		when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(expectedReview));
		
		Review actualReview = underTest.getReviewById(reviewId);
		
		assertEquals(expectedReview, actualReview);
		
	}
	
	@Test
	void getReviewById_ShouldThrowResourceNotFoundException_UponInvalidInput() {
		Integer reviewId = 7;
		
		when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> underTest.getReviewById(reviewId));	
	}

}
