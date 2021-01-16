package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.entity.User;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user = userConverter.fromDto(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }
}
