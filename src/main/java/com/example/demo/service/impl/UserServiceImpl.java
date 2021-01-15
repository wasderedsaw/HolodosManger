package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.entity.User;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        return userRepository.save(userConverter.fromDto(createUserDto));
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
