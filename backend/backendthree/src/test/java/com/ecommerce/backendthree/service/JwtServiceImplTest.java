package com.ecommerce.backendthree.service;

import com.ecommerce.backendthree.entity.User;
import com.ecommerce.backendthree.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTest {
    private JwtServiceImpl underTest = new JwtServiceImpl();

    @Test
    void encode_ShouldReturnToken_UponValidUser() {

        User user = User.builder()
                .userId(1)
                .userName("User1")
                .email("user1@email.com")
                .build();

        String token = underTest.encode(user);

        assertNotNull(token);
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void encode_ShouldThrowException_UponInvalidUser() {

        User user = User.builder()
                .userName("User1")
                .build();

        assertThrows(RuntimeException.class, () -> underTest.encode(user));
    }

    @Test
    void encode_ShouldThrowException_UponNullUser() {

        assertThrows(RuntimeException.class, () -> underTest.encode(null));
    }

    @Test
    void decode_ShouldReturnUser_UponValidToken() {

        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ1c2VyMUBlbWFpbC5jb20iLCJ1c2VyTmFtZSI6IlVzZXIxIiwiaWF0IjoxNjg1MjA4OTYwfQ.QHuNtuAuz8Vmqk4GAh1Y1Pk5ZMS-01svCvLlAHXWhGNGKioKjVYIE1jB2XX1dFWZ";

        User expectedUser = User.builder()
                .userId(1)
                .userName("User1")
                .email("user1@email.com")
                .token(token)
                .build();

        User actualUser = underTest.decode(token);

        assertEquals(expectedUser, actualUser);

    }

    @Test
    void decode_ShouldThrowException_UponInvalidToken() {

        assertThrows(RuntimeException.class, () -> underTest.decode("ajkshdajhdadsjkhajkshda"));
        assertThrows(RuntimeException.class, () -> underTest.decode("ajkshdajhdadsjkha.asdajhdadhsa.iuhsadjksadsahda"));
    }

    @Test
    void decode_ShouldThrowException_UponNullToken() {

        assertThrows(RuntimeException.class, () -> underTest.decode(null));
    }

}
