package com.ecommerce.backendone.service;

import com.ecommerce.backendone.entity.Review;
import com.ecommerce.backendone.payload.User;

public interface ReviewService {

	Review addReview(Review review, User user);

	void deleteReview(Review review, User user);

	Review getReviewById(Integer reviewId);
}
