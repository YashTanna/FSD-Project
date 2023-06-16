package com.ecommerce.backendone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendone.entity.Product;
import com.ecommerce.backendone.entity.Review;
import com.ecommerce.backendone.exception.NotAuthorizedException;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.payload.User;
import com.ecommerce.backendone.repository.ReviewRepository;
import com.ecommerce.backendone.service.ProductService;
import com.ecommerce.backendone.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public Review addReview(Review review, User user) {
		if (review.getUserId().intValue() != user.getUserId().intValue())
            throw new NotAuthorizedException();
        review.setUserName(user.getUserName());
        Review savedReview = reviewRepository.save(review);
    	Product product = productService.getProductById(review.getProduct().getProductId());
        product.setReviewCount(product.getReviewCount() + 1);
        product.setTotalRating(product.getTotalRating() + review.getRating());
        productService.updateProduct(product);
        return savedReview;
	}
	
	@Override
	public void deleteReview(Review review, User user) {
		if (review.getUserId().intValue() != user.getUserId().intValue())
            throw new NotAuthorizedException();
		Review deletedReview = getReviewById(review.getReviewId());
        reviewRepository.deleteById(review.getReviewId());
        Product product = productService.getProductById(review.getProduct().getProductId());
        product.setReviewCount(product.getReviewCount() - 1);
        product.setTotalRating(product.getTotalRating() - deletedReview.getRating());
        productService.updateProduct(product);
	}
	
	@Override
	public Review getReviewById(Integer reviewId) {
		return reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review with id: " + reviewId + " not found"));
	}
}
