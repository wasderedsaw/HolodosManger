package com.example.demo.converter;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ViewUserDto;
import com.example.demo.entity.User;

public interface UserConverter {
    User fromDto(CreateUserDto createUserDto);
    ViewUserDto toDto(User user);
}
