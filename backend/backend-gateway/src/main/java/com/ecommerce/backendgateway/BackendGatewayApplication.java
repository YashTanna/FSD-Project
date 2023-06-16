package com.ecommerce.backendgateway;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class BackendGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/sections/**", "/categories/**", "/products/**", "/reviews/**")
						.uri("http://localhost:8081"))
				.route(r -> r.path("/cart/**", "/orders/**")
						.uri("http://localhost:8082"))
				.route(r -> r.path("/users/**")
						.uri("http://localhost:8083"))
				.build();
	}
	
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setAllowedHeaders(Collections.singletonList("*"));
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return new CorsWebFilter(source);
	}

}
