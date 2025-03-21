package com.ecommerce.backendthree.dto.mapper;

import com.ecommerce.backendthree.dto.UserDto;
import com.ecommerce.backendthree.entity.User;

public class UserMapper {
    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .token(user.getToken())
                .build();
    }

    public static User mapToEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .token(userDto.getToken())
                .build();
    }
}
