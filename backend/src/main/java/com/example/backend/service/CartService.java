package com.example.backend.service;

import com.example.backend.dto.CartItemRequestDTO;
import com.example.backend.entities.CartItem;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.repository.CartItemRepository;
import com.example.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    // Get all cart items for the logged-in user
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    // Add an item to the cart
    public CartItem addCartItem(User user, CartItemRequestDTO request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setId(UUID.randomUUID().toString());
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setCreatedAt(LocalDate.now());
        cartItem.setUpdatedAt(LocalDate.now());

        return cartItemRepository.save(cartItem);
    }

    // Update quantity of a cart item
    public CartItem updateCartItem(String itemId, int quantity, User user) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

        if (!cartItem.getUser().getId().equals(user.getId())) {
            throw new SecurityException("Unauthorized access to update cart item");
        }

        cartItem.setQuantity(quantity);
        cartItem.setUpdatedAt(LocalDate.now());

        return cartItemRepository.save(cartItem);
    }

    // Remove an item from the cart
    public void removeCartItem(String itemId, User user) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

        if (!cartItem.getUser().getId().equals(user.getId())) {
            throw new SecurityException("Unauthorized access to remove cart item");
        }

        cartItemRepository.delete(cartItem);
    }
}
