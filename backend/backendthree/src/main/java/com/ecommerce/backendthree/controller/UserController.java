package com.ecommerce.backendthree.controller;

import com.ecommerce.backendthree.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Validated
public interface UserController {

	@PostMapping("/login")
	ResponseEntity<UserDto> loginUser(@RequestBody @Validated(UserDto.Login.class) UserDto userDto);

	@PostMapping("/register")
	ResponseEntity<UserDto> registerUser(@RequestBody @Validated(UserDto.Register.class) UserDto userDto);

	@GetMapping("/validate")
	ResponseEntity<UserDto> validateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String token);

}
