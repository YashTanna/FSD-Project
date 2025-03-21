package com.ecommerce.backendtwo.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendtwo.controller.OrderController;
import com.ecommerce.backendtwo.dto.OrderItemDto;
import com.ecommerce.backendtwo.dto.mapper.OrderItemMapper;
import com.ecommerce.backendtwo.entity.OrderItem;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.service.FetchService;
import com.ecommerce.backendtwo.service.OrderItemService;

@RestController
public class OrderControllerImpl implements OrderController {
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private FetchService fetchService;

	@Override
	public ResponseEntity<List<OrderItemDto>> getOrders(String token) {
		User user = fetchService.getUser(token);
		List<OrderItem> orders = orderItemService.getOrdersOfUser(user);
		List<OrderItemDto> orderDtos = orders.stream()
				.map(OrderItemMapper::mapToDto)
				.toList();
		return new ResponseEntity<>(orderDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addOrders(String token) {
		User user = fetchService.getUser(token);
		orderItemService.addOrdersOfUser(user.getUserId());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
