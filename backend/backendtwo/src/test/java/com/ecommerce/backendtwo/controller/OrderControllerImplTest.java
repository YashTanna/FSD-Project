package com.ecommerce.backendtwo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.backendtwo.controller.impl.OrderControllerImpl;
import com.ecommerce.backendtwo.dto.OrderItemDto;
import com.ecommerce.backendtwo.dto.mapper.OrderItemMapper;
import com.ecommerce.backendtwo.entity.OrderItem;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.service.FetchService;
import com.ecommerce.backendtwo.service.OrderItemService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerImplTest {
	@Mock
	private OrderItemService orderItemService;
	@Mock
	private FetchService fetchService;
	@InjectMocks
	private OrderControllerImpl underTest;
	
	@Test
	void getOrders_ShouldReturnCorrectResponse() {
		String token = "token";
		User user = User.builder().build();
		List<OrderItem> orderItems = List.of(
				OrderItem.builder().build(),
				OrderItem.builder().build()
				);
		List<OrderItemDto> orderItemDtos = orderItems.stream()
				.map(OrderItemMapper::mapToDto)
				.toList();
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(orderItemService.getOrdersOfUser(user)).thenReturn(orderItems);;
		
		ResponseEntity<List<OrderItemDto>> response = underTest.getOrders(token);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orderItemDtos, response.getBody());
	}
	
	@Test
	void addOrders_ShouldReturnCorrectResponse() {
		Integer userId = 1;
		String token = "token";
		User user = User.builder().userId(userId).build();
		
		when(fetchService.getUser(token)).thenReturn(user);
		
		ResponseEntity<Void> response = underTest.addOrders(token);
		
		verify(orderItemService, times(1)).addOrdersOfUser(userId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}
