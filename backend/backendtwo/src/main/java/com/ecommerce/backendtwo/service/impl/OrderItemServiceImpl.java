package com.ecommerce.backendtwo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.entity.OrderItem;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.repository.OrderItemRepository;
import com.ecommerce.backendtwo.service.CartItemService;
import com.ecommerce.backendtwo.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CartItemService cartItemService;

	@Override
	public List<OrderItem> getOrdersOfUser(User user) {
		List<OrderItem> orders = orderItemRepository.findByUserId(user.getUserId());
		if (orders == null)
			return new ArrayList<>();
		return orders;
	}

	@Override
	public void addOrdersOfUser(Integer userId) {
		List<CartItem> cartItems = cartItemService.getCartItems(userId);
		List<Integer> cartItemIds = cartItems.stream()
				.map(CartItem::getCartItemId)
				.toList();
		List<OrderItem> orderItems = cartItems.stream()
				.map(x -> OrderItem.builder()
						.userId(userId)
						.product(x.getProduct())
						.quantity(x.getQuantity())
						.totalPrice(x.getQuantity() * x.getProduct().getSellingPrice())
						.build())
				.toList();

		cartItemService.removeCartItems(cartItemIds);
		orderItemRepository.saveAll(orderItems);
	}

}
