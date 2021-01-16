package com.example.demo.converter.impl;

import com.example.demo.converter.UserDetailsConverter;
import com.example.demo.dto.UserDetailsDto;
import com.example.demo.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDetailsConverterImpl implements UserDetailsConverter {

    @Override
    public UserDetailsDto toDto(User user) {
        return new UserDetailsDto(user.getUserName(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
