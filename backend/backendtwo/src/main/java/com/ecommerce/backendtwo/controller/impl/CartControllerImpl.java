package com.ecommerce.backendtwo.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendtwo.controller.CartController;
import com.ecommerce.backendtwo.dto.CartDto;
import com.ecommerce.backendtwo.dto.CartItemDto;
import com.ecommerce.backendtwo.dto.mapper.CartItemMapper;
import com.ecommerce.backendtwo.dto.mapper.CartMapper;
import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.payload.User;
import com.ecommerce.backendtwo.service.CartItemService;
import com.ecommerce.backendtwo.service.FetchService;

@RestController
public class CartControllerImpl implements CartController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private FetchService fetchService;

	@Override
	public ResponseEntity<CartDto> getCart(String token) {
		User user = fetchService.getUser(token);
		List<CartItem> cartItems = cartItemService.getCartItems(user.getUserId());
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addItemToCart(String token, Integer productId) {
		User user = fetchService.getUser(token);
		cartItemService.addCartItem(user.getUserId(), productId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CartDto> removeItemFromCart(String token, Integer cartItemId) {
		User user = fetchService.getUser(token);
		cartItemService.removeCartItem(cartItemId);
		List<CartItem> cartItems = cartItemService.getCartItems(user.getUserId());
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CartDto> updateItemInCart(String token, CartItemDto cartItemDto) {
		User user = fetchService.getUser(token);
		CartItem cartItem = CartItemMapper.mapToEntity(cartItemDto);
		cartItemService.updateCartItem(cartItem);
		List<CartItem> cartItems = cartItemService.getCartItems(user.getUserId());
		CartDto cartDto = CartMapper.mapToDto(cartItems);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CartItemDto> getItemFromCart(String token, Integer productId) {
		User user = fetchService.getUser(token);
		CartItem cartItem = cartItemService.getCartItem(user.getUserId(), productId);
		CartItemDto cartItemDto = CartItemMapper.mapToDto(cartItem);
		return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
	}
}
