package com.ecommerce.backendthree.controller.impl;

import com.ecommerce.backendthree.controller.UserController;
import com.ecommerce.backendthree.dto.UserDto;
import com.ecommerce.backendthree.dto.mapper.UserMapper;
import com.ecommerce.backendthree.entity.User;
import com.ecommerce.backendthree.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserControllerImpl implements UserController {
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<UserDto> loginUser(UserDto userDto) {
		log.info("Received request at UserController - loginUser()");
		User user = UserMapper.mapToEntity(userDto);
		User existingUser = userService.loginUser(user);
		return new ResponseEntity<>(UserMapper.mapToDto(existingUser), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> registerUser(UserDto userDto) {
		log.info("Received request at UserController - registerUser()");
		User user = UserMapper.mapToEntity(userDto);
		User newUser = userService.registerUser(user);
		return new ResponseEntity<>(UserMapper.mapToDto(newUser), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDto> validateUser(String token) {
		log.info("Received request at UserController - validateUser()");
		User user = userService.validateUser(token);
		return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.OK);
	}

}
