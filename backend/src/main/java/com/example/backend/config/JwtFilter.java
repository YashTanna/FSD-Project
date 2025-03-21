package com.example.backend.config;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        // Define public endpoints (they won't be processed by this filter)
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/auth/register") ||
            requestURI.equals("/api/auth/login") ||
            requestURI.equals("/api/auth/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Debug: Print the Authorization header (if any)
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authHeader);

        String jwtToken = null;
        String userEmail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                userEmail = jwtService.extractUsername(jwtToken);
                System.out.println("Extracted username from token: " + userEmail);
            } catch (Exception e) {
                System.out.println("Error extracting username from token: " + e.getMessage());
            }
        } else {
            System.out.println("No Bearer token found in header.");
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("User authenticated: " + userDetails.getUsername());
            } else {
                System.out.println("UserDetails returned null for: " + userEmail);
            }
        } else {
            System.out.println("User email is null or authentication already set.");
        }

        filterChain.doFilter(request, response);
    }
}
