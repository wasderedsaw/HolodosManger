package com.example.demo.converter.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ViewUserDto;
import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public User fromDto(CreateUserDto createUserDto) {
        return User.builder()
                .id(null)
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .middleName(createUserDto.getMiddleName())
                .userName(createUserDto.getUserName())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .role(Roles.USER)
                .build();
    }

    @Override
    public ViewUserDto toDto(User user) {
        return ViewUserDto.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .userName(user.getUserName())
                .role(user.getRole().toString())
                .build();
    }
}
