package com.ecommerce.backendtwo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.exception.ResourceNotFoundException;
import com.ecommerce.backendtwo.payload.Product;
import com.ecommerce.backendtwo.repository.CartItemRepository;
import com.ecommerce.backendtwo.service.CartItemService;
import com.ecommerce.backendtwo.service.FetchService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private FetchService fetchService;

	@Override
	public CartItem getCartItem(Integer userId, Integer productId) {
		CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
		if (cartItem == null)
			throw new ResourceNotFoundException("CartItem not found");
		return cartItem;
	}

	@Override
	public List<CartItem> getCartItems(Integer userId) {
		List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
		List<Integer> productIds = cartItems.stream()
				.map(CartItem::getProductId)
				.toList();
		List<Product> products = fetchService.getProductsById(productIds);
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			cartItem.setProduct(products.get(i));
		}
		return cartItems;
	}

	@Override
	public void addCartItem(Integer userId, Integer productId) {
		CartItem cartItem = CartItem.builder()
				.userId(userId)
				.productId(productId)
				.quantity(1)
				.build();
		cartItemRepository.save(cartItem);
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		CartItem savedCartItem = cartItemRepository.findById(cartItem.getCartItemId())
				.orElseThrow(() -> new ResourceNotFoundException("Could not find item in cart"));
		savedCartItem.setQuantity(cartItem.getQuantity());
		cartItemRepository.save(savedCartItem);
	}

	@Override
	public void removeCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}

	@Override
	public void removeCartItems(List<Integer> cartItemIds) {
		cartItemRepository.deleteAllById(cartItemIds);
	}

}
