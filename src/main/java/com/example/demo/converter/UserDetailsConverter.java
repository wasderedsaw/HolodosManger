package com.example.demo.converter;

import com.example.demo.dto.UserDetailsDto;
import com.example.demo.entity.User;

public interface UserDetailsConverter {
    UserDetailsDto toDto(User user);
}
