package com.ecommerce.backendtwo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendtwo.entity.CartItem;
import com.ecommerce.backendtwo.exception.ResourceNotFoundException;
import com.ecommerce.backendtwo.payload.Product;
import com.ecommerce.backendtwo.repository.CartItemRepository;
import com.ecommerce.backendtwo.service.impl.CartItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {
	@Mock
	private CartItemRepository cartItemRepository;
	@Mock
	private FetchService fetchService;
	@InjectMocks
	private CartItemServiceImpl underTest;
	
	@Test
	void getCartItem_ShouldReturnCartItem_UponValidInput() {
		Integer userId = 1;
		Integer productId = 2;
		CartItem expectedCartItem = CartItem.builder().cartItemId(1).build();
		
		when(cartItemRepository.findByUserIdAndProductId(userId, productId)).thenReturn(expectedCartItem);
		
		CartItem actualCartItem = underTest.getCartItem(userId, productId);
		
		assertEquals(expectedCartItem, actualCartItem);
	}
	
	@Test
	void getCartItem_ShouldThrowResourceNotFoundException_UponInvalidInput() {
		Integer userId = 1;
		Integer productId = 2;
		
		when(cartItemRepository.findByUserIdAndProductId(userId, productId)).thenReturn(null);
		
		assertThrows(ResourceNotFoundException.class, () -> underTest.getCartItem(userId, productId));
	}
	
	@Test
	void getCartItems_ShouldReturnCartItems_UponValidInput() {
		Integer userId = 1;
		List<Integer> productIds = List.of(1, 2, 3);
		List<Product> products = productIds.stream()
				.map(x -> Product.builder().productId(x).build())
				.toList();
		List<CartItem> cartItems = productIds.stream()
				.map(x -> CartItem.builder().productId(x).build())
				.toList();
		List<CartItem> expectedCartItems = products.stream()
				.map(x -> CartItem.builder().productId(x.getProductId()).product(x).build())
				.toList();
		
		when(cartItemRepository.findByUserId(userId)).thenReturn(cartItems);
		when(fetchService.getProductsById(productIds)).thenReturn(products);
		
		List<CartItem> actualCartItems = underTest.getCartItems(userId);
		
		assertEquals(expectedCartItems, actualCartItems);
	}
	
	@Test
	void addCartItem_ShouldAddCartItem_UponValidInput() {
		Integer userId = 1;
		Integer productId = 2;
		CartItem cartItem = CartItem.builder()
				.userId(userId)
				.productId(productId)
				.quantity(1)
				.build();
				
		underTest.addCartItem(userId, productId);
		
		verify(cartItemRepository, times(1)).save(cartItem);
	}
	
	@Test
	void updateCartItem_ShouldUpdateCartItem_UponValidInput() {
		Integer cartItemId = 1;
		Integer oldQuantity = 2;
		Integer newQuantity = 3;
		CartItem sentCartItem = CartItem.builder().cartItemId(cartItemId).quantity(newQuantity).build();
		CartItem oldCartItem = CartItem.builder().cartItemId(cartItemId).quantity(oldQuantity).build();
		CartItem updatedCartItem = CartItem.builder().cartItemId(cartItemId).quantity(newQuantity).build();
				
		when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(oldCartItem));
		
		underTest.updateCartItem(sentCartItem);
		
		verify(cartItemRepository, times(1)).save(updatedCartItem);
	}
	
	@Test
	void removeCartItem_ShouldDeleteCartItem_UponValidInput() {
		Integer cartItemId = 1;
		
		underTest.removeCartItem(cartItemId);
		
		verify(cartItemRepository, times(1)).deleteById(cartItemId);
	}
	
	@Test
	void removeCartItems_ShouldDeleteCartItems_UponValidInput() {
		List<Integer> cartItemIds = List.of(1, 2, 3);
		
		underTest.removeCartItems(cartItemIds);
		
		verify(cartItemRepository, times(1)).deleteAllById(cartItemIds);
	}

}
