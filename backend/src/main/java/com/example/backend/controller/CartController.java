package com.example.backend.controller;

import com.example.backend.dto.CartItemRequestDTO;
import com.example.backend.entities.CartItem;
import com.example.backend.entities.User;
import com.example.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 1. Get Cart: returns the current user's cart items
    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println("Authenticated user: " + user);

        List<CartItem> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }

    // 2. Add to Cart: adds a product to the cart
    @PostMapping
    public ResponseEntity<CartItem> addToCart(@AuthenticationPrincipal User user,
            @RequestBody CartItemRequestDTO request) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        CartItem cartItem = cartService.addCartItem(user, request);
        return ResponseEntity.ok(cartItem);
    }

    // 3. Update Cart Item: updates quantity of a cart item
    @PutMapping("/{itemId}")
    public ResponseEntity<CartItem> updateCartItem(@AuthenticationPrincipal User user,
            @PathVariable String itemId,
            @RequestParam int quantity) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        CartItem updatedItem = cartService.updateCartItem(itemId, quantity, user);
        return ResponseEntity.ok(updatedItem);
    }

    // 4. Remove Cart Item: removes an item from the cart
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeCartItem(@AuthenticationPrincipal User user,
            @PathVariable String itemId) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        cartService.removeCartItem(itemId, user);
        return ResponseEntity.noContent().build();
    }
}
