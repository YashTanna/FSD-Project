package com.ecommerce.backendtwo.service;

import java.util.List;

import com.ecommerce.backendtwo.entity.OrderItem;
import com.ecommerce.backendtwo.payload.User;

public interface OrderItemService {
	List<OrderItem> getOrdersOfUser(User user);

	void addOrdersOfUser(Integer userId);
}
