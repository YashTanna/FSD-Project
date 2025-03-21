package com.example.backend.controller;

import com.example.backend.config.JwtService;
import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.SignupRequest;
import com.example.backend.entities.User;
import com.example.backend.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Register - creates a new user account
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        // Check if a user with the given email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "User with that email already exists."));
        }

        // Create a new user; generate a UUID for the String id.
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("user"); // Default role, adjust as needed
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User created successfully."));
    }

    // 2. Login - authenticates the user and returns a JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }

        // Retrieve the user from the database (assumes email is used as the username)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }

    // 3. Logout - ends the user's session (in stateless JWT, this is typically a
    // client-side operation)
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // For stateless JWT, simply clear the SecurityContext.
        // You could also instruct the client to delete the token.
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "User logged out successfully."));
    }

    // 4. Get Current User - returns the current authenticated user's information
    // @GetMapping("/me")
    // public ResponseEntity<?> getCurrentUser(Authentication authentication) {
    // if (authentication == null || !authentication.isAuthenticated() ||
    // "anonymousUser".equals(authentication.getPrincipal())) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    // .body(Map.of("error", "No authenticated user"));
    // }
    // // Optionally, log the principal
    // System.out.println("Current authenticated principal: " +
    // authentication.getPrincipal());
    // return ResponseEntity.ok(Map.of("user", authentication.getPrincipal()));
    // }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "No authenticated user"));
        }
        return ResponseEntity.ok(authentication.getPrincipal());
    }

}
