package com.ecommerce.backendthree.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendthree.entity.User;
import com.ecommerce.backendthree.exception.UnauthorizedException;
import com.ecommerce.backendthree.repository.UserRepository;
import com.ecommerce.backendthree.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private JwtService jwtService;
	@InjectMocks
	private UserServiceImpl underTest;
	
	@Test
	void loginUser_ShouldReturnUser_UponCorrectCredentials() {

		User user = User.builder()
				.email("e1@email.com")
				.password("pass")
				.build();

		User expectedUser = User.builder()
				.userId(1)
				.userName("User1")
				.email(user.getEmail())
				.password(user.getPassword())
				.build();

		String token = "abcdefgh.abcdefgh.abcdefgh";
		
		when(userRepository.findByEmail(user.getEmail())).thenReturn(expectedUser);
		when(jwtService.encode(expectedUser)).thenReturn(token);
		
		User actualUser = underTest.loginUser(user);

		assertEquals(expectedUser, actualUser);
	}
	
	@Test
	void loginUser_ShouldThrowUnauthorizedException_UponUnregisteredUser() {
		
		User user = User.builder()
				.email("e1@email.com")
				.password("pass")
				.build();
				
		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		
		assertThrows(UnauthorizedException.class, () -> underTest.loginUser(user));
		
	}
	
	@Test
	void loginUser_ShouldThrowUnauthorizedException_UponInvalidCredentials() {
		
		User user = User.builder()
				.email("e1@email.com")
				.password("pass")
				.build();
		
		User foundUser = User.builder()
				.userId(1)
				.userName("User1")
				.email(user.getEmail())
				.password("diffpass")
				.build();
		
		when(userRepository.findByEmail(user.getEmail())).thenReturn(foundUser);
		
		assertThrows(UnauthorizedException.class, () -> underTest.loginUser(user));
				
	}
	
	@Test
	void registerUser_ShouldReturnUser_UponCorrectCredentials() {
		
		User user = User.builder()
				.userName("User1")
				.email("e1@email.com")
				.password("pass")
				.build();

		User expectedUser = User.builder()
				.userId(1)
				.userName(user.getUserName())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();

		String token = "abcdefgh.abcdefgh.abcdefgh";
		
		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		when(userRepository.save(user)).thenReturn(expectedUser);
		when(jwtService.encode(expectedUser)).thenReturn(token);
		
		User actualUser = underTest.registerUser(user);
		
		assertEquals(expectedUser, actualUser);
	}

	@Test
	void registerUser_ShouldSaveUser_UponCorrectCredentials() {

		User user = User.builder()
				.userName("User1")
				.email("e1@email.com")
				.password("pass")
				.build();

		User newUser = User.builder()
				.userId(1)
				.userName(user.getUserName())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();

		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		when(userRepository.save(user)).thenReturn(newUser);

		underTest.registerUser(user);

		verify(userRepository, times(1)).save(user);
	}
	
	@Test
	void registerUser_ShouldThrowUnauthorizedException_UponEmailAlreadyExists() {
		
		User user = User.builder()
				.email("e1@email.com")
				.password("pass")
				.build();
		
		User foundUser = User.builder()
				.userId(1)
				.userName("User1")
				.email("e1@email.com")
				.password("diffpass")
				.build();
		
		when(userRepository.findByEmail("e1@email.com")).thenReturn(foundUser);
		
		assertThrows(UnauthorizedException.class, () -> underTest.registerUser(user));
		
	}
}
