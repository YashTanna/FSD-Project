package com.ecommerce.backendthree.service.impl;

import com.ecommerce.backendthree.entity.User;
import com.ecommerce.backendthree.exception.UnauthorizedException;
import com.ecommerce.backendthree.repository.UserRepository;
import com.ecommerce.backendthree.service.JwtService;
import com.ecommerce.backendthree.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;

	@Override
	public User loginUser(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser == null)
			throw new UnauthorizedException("Email not registered");
		if (!existingUser.getPassword().equals(user.getPassword()))
			throw new UnauthorizedException("Invalid credentials");
		existingUser.setToken(jwtService.encode(existingUser));
		log.info("User logged in successfully: " + existingUser);
		return existingUser;
	}

	@Override
	public User registerUser(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null)
			throw new UnauthorizedException("Email already registered");
		User newUser = userRepository.save(user);
		newUser.setToken(jwtService.encode(newUser));
		log.info("User registered successfully: " + newUser);
		return newUser;
	}
	
	@Override
	public User validateUser(String token) {
		User user = jwtService.decode(token);
		log.info("User validated successfully: " + user);
		return user;
	}

}