package com.ecommerce.backendthree.controller;

import com.ecommerce.backendthree.controller.impl.UserControllerImpl;
import com.ecommerce.backendthree.dto.UserDto;
import com.ecommerce.backendthree.dto.mapper.UserMapper;
import com.ecommerce.backendthree.entity.User;
import com.ecommerce.backendthree.service.UserService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerImplTest {
    @Mock
    private UserService userService;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @InjectMocks
    private UserControllerImpl underTest;

    @Test
    void loginUser_ShouldReturnUserDto_UponValidInputs() {

        UserDto userDto = UserDto.builder()
                .email("e1@email.com")
                .password("pass")
                .build();

        User user = UserMapper.mapToEntity(userDto);

        User foundUser = User.builder()
                .userId(1)
                .userName("User1")
                .email(user.getEmail())
                .password(user.getPassword())
                .token("abcdefgh.abcdefgh.abcdefgh")
                .build();

        UserDto expectedUserDto = UserMapper.mapToDto(foundUser);

        when(userService.loginUser(user)).thenReturn(foundUser);

        ResponseEntity<UserDto> response = underTest.loginUser(userDto);
        UserDto actualUserDto = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void loginUser_ShouldThrowException_UponInvalidInputs() {

        UserDto userDto = UserDto.builder()
                .email("e1email.com")
                .password("pas")
                .build();

        int numberOfErrors = validator.validate(userDto, UserDto.Login.class).size();

        assertEquals(2, numberOfErrors);
    }

    @Test
    void registerUser_ShouldReturnUserDto_UponValidInputs() {

        UserDto userDto = UserDto.builder()
                .userName("User1")
                .email("e1@email.com")
                .password("pass")
                .build();

        User user = UserMapper.mapToEntity(userDto);

        User newUser = User.builder()
                .userId(1)
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .token("abcdefgh.abcdefgh.abcdefgh")
                .build();

        UserDto expectedUserDto = UserMapper.mapToDto(newUser);

        when(userService.registerUser(user)).thenReturn(newUser);

        ResponseEntity<UserDto> response = underTest.registerUser(userDto);
        UserDto actualUserDto = response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void registerUser_ShouldThrowException_UponInvalidInputs() {

        UserDto userDto = UserDto.builder()
                .userName("User1#%")
                .email("e1email.com")
                .password("pas")
                .build();

        int numberOfErrors = validator.validate(userDto, UserDto.Register.class).size();

        assertEquals(3, numberOfErrors);
    }

}
