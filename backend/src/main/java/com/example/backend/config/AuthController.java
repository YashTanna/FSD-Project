// package com.example.backend.config;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.Data;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {
//     @Autowired
//     private AuthenticationManager authenticationManager;
//     @Autowired
//     private JwtUtil jwtUtil;
//     @Autowired
//     private CustomUserDetailsService userDetailsService;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//         String token = jwtUtil.generateToken(request.getEmail());
//         return ResponseEntity.ok(Map.of("token", token));
//     }
// }

// @Data
// class AuthRequest {
//     private String email;
//     private String password;
// }

