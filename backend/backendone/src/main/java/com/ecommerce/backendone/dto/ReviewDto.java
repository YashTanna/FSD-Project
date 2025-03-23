package com.ecommerce.backendone.dto;

import java.util.Date;

import com.ecommerce.backendone.entity.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
	@NotNull(message = "Review ID cannot be null", groups = { DeleteReview.class })
	private Integer reviewId;

	@NotNull(message = "User ID cannot be null", groups = { AddReview.class })
	private Integer userId;

	private String userName;

	@NotNull(message = "Rating cannot be null", groups = { AddReview.class })
	private Integer rating;

	@NotBlank(message = "Text cannot be blank", groups = { AddReview.class })
	private String text;

	private Date createdAt;

	@NotNull(message = "Product cannot be null", groups = { AddReview.class })
	private Product product;

	public interface AddReview {
	}

	public interface DeleteReview {
	}
}
