package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.entity.User;

public interface UserService {
    User createUser(CreateUserDto createUserDto);
    User getUserById(Long id);
}
