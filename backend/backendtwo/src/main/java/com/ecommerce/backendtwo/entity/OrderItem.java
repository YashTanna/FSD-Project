package com.ecommerce.backendtwo.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.ecommerce.backendtwo.payload.Product;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
	@Id
	@GeneratedValue
	private Integer orderItemId;
	private Integer totalPrice;
	private Integer quantity;
	@CreationTimestamp
	private Date orderDate;
	@Embedded
	private Product product;
	private Integer userId;
}
