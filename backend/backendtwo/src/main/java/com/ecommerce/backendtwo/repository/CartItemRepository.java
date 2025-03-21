package com.ecommerce.backendtwo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backendtwo.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByUserId(Integer userId);

	CartItem findByUserIdAndProductId(Integer userId, Integer productId);
}
