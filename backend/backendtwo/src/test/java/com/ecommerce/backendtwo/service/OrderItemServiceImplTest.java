package com.ecommerce.backendtwo.service;

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

import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.entity.OrderItem;
import com.ecommerce.backendtwo.payload.Product;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.repository.OrderItemRepository;
import com.ecommerce.backendtwo.service.impl.OrderItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceImplTest {
	@Mock
	private OrderItemRepository orderItemRepository;
	@Mock
	private CartItemService cartItemService;
	@InjectMocks
	private OrderItemServiceImpl underTest;
	
	@Test
	void getOrdersOfUser_ShouldReturnOrderItems_UponValidInput() {
		User user = User.builder().userId(1).build();
		List<OrderItem> expectedOrderItems  = List.of(
				OrderItem.builder().build(),
				OrderItem.builder().build()
				);
		
		when(orderItemRepository.findByUserId(user.getUserId())).thenReturn(expectedOrderItems);
		
		List<OrderItem> actualOrderItems = underTest.getOrdersOfUser(user);
		
		assertEquals(expectedOrderItems, actualOrderItems);
	}
	
	@Test
	void addOrdersOfUser_ShouldDeleteCartItems() {
		Integer userId = 1;
		Product product = Product.builder().sellingPrice(100).build();
		List<Integer> cartItemIds = List.of(1, 2, 3);
		List<CartItem> cartItems = cartItemIds.stream()
				.map(x -> CartItem.builder().cartItemId(x).quantity(1).product(product).build())
				.toList();
		
		when(cartItemService.getCartItems(userId)).thenReturn(cartItems);
		
		underTest.addOrdersOfUser(userId);
		
		verify(cartItemService, times(1)).removeCartItems(cartItemIds);
	}
	
	@Test
	void addOrdersOfUser_ShouldAddOrderItems() {
		Integer userId = 1;
		Product product = Product.builder().sellingPrice(100).build();
		List<Integer> cartItemIds = List.of(1, 2, 3);
		List<CartItem> cartItems = cartItemIds.stream()
				.map(x -> CartItem.builder().cartItemId(x).quantity(1).product(product).build())
				.toList();
		List<OrderItem> orderItems = cartItems.stream()
				.map(x -> OrderItem.builder()
						.userId(userId)
						.product(x.getProduct())
						.quantity(x.getQuantity())
						.totalPrice(x.getQuantity() * x.getProduct().getSellingPrice())
						.build())
				.toList();
		
		when(cartItemService.getCartItems(userId)).thenReturn(cartItems);
		
		underTest.addOrdersOfUser(userId);
		
		verify(orderItemRepository, times(1)).saveAll(orderItems);
	}

}
