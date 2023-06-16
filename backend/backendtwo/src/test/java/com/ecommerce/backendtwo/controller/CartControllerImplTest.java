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

import com.ecommerce.backendtwo.controller.impl.CartControllerImpl;
import com.ecommerce.backendtwo.dto.CartDto;
import com.ecommerce.backendtwo.dto.CartItemDto;
import com.ecommerce.backendtwo.dto.mapper.CartItemMapper;
import com.ecommerce.backendtwo.dto.mapper.CartMapper;
import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.payload.Product;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.service.CartItemService;
import com.ecommerce.backendtwo.service.FetchService;

@ExtendWith(MockitoExtension.class)
public class CartControllerImplTest {
	@Mock
	private CartItemService cartItemService;
	@Mock
	private FetchService fetchService;
	@InjectMocks
	private CartControllerImpl underTest;
	
	private List<CartItem> generateCartItems() {
		Product product = Product.builder().sellingPrice(100).build();
		List<CartItem> cartItems = List.of(
				CartItem.builder().quantity(2).product(product).build(),
				CartItem.builder().quantity(2).product(product).build()
				);
		return cartItems;
	}
	
	@Test
	void getCart_ShouldReturnCorrectResult() {
		Integer userId = 1;
		String token = "token";
		User user = User.builder().userId(userId).build();
		List<CartItem> cartItems = generateCartItems();
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(cartItemService.getCartItems(userId)).thenReturn(cartItems);
		
		ResponseEntity<CartDto> response = underTest.getCart(token);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartDto, response.getBody());
	}
	
	@Test
	void addItemToCart_ShouldReturnCorrectResult() {
		Integer userId = 1;
		Integer productId = 1;
		String token = "token";
		User user = User.builder().userId(userId).build();
		
		when(fetchService.getUser(token)).thenReturn(user);
		
		ResponseEntity<Void> response = underTest.addItemToCart(token, productId);
		
		verify(cartItemService, times(1)).addCartItem(userId, productId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void removeItemFromCart_ShouldReturnCorrectResult() {
		Integer userId = 1;
		Integer cartItemId = 5;
		String token = "token";
		User user = User.builder().userId(userId).build();
		List<CartItem> cartItems = generateCartItems();
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(cartItemService.getCartItems(userId)).thenReturn(cartItems);
		
		ResponseEntity<CartDto> response = underTest.removeItemFromCart(token, cartItemId);
		
		verify(cartItemService, times(1)).removeCartItem(cartItemId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartDto, response.getBody());
	}
	
	@Test
	void updateItemInCart_ShouldReturnCorrectResult() {
		Integer userId = 1;
		Integer cartItemId = 5;
		String token = "token";
		User user = User.builder().userId(userId).build();
		List<CartItem> cartItems = generateCartItems();
		CartItem cartItem = CartItem.builder().cartItemId(cartItemId).build();
		CartItemDto cartItemDto = CartItemMapper.mapToDto(cartItem);
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(cartItemService.getCartItems(userId)).thenReturn(cartItems);
		
		ResponseEntity<CartDto> response = underTest.updateItemInCart(token, cartItemDto);
		
		verify(cartItemService, times(1)).updateCartItem(cartItem);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartDto, response.getBody());
		
	}
	
	@Test
	void getItemFromCart_ShouldReturnCorrectResult() {
		Integer userId = 1;
		Integer productId = 5;
		String token = "token";
		User user = User.builder().userId(userId).build();
		CartItem cartItem = CartItem.builder().build();
		CartItemDto cartItemDto = CartItemMapper.mapToDto(cartItem);
		
		when(fetchService.getUser(token)).thenReturn(user);
		when(cartItemService.getCartItem(userId, productId)).thenReturn(cartItem);
		
		ResponseEntity<CartItemDto> response = underTest.getItemFromCart(token, productId);
		
		verify(cartItemService, times(1)).getCartItem(userId, productId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartItemDto, response.getBody());
	}

}
