package com.ecommerce.backendtwo.service;

import java.util.List;

import com.ecommerce.backendtwo.entity.CartItem;

public interface CartItemService {
	CartItem getCartItem(Integer userId, Integer productId);

	List<CartItem> getCartItems(Integer userId);

	void addCartItem(Integer userId, Integer productId);

	void updateCartItem(CartItem cartItem);

	void removeCartItem(Integer cartItemId);

	void removeCartItems(List<Integer> cartItemIds);
}
