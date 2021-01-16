package com.example.demo.service.impl;

import com.example.demo.converter.UserDetailsConverter;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsConverter userDetailsConverter;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserDetailsConverter userDetailsConverter, UserRepository userRepository) {
        this.userDetailsConverter = userDetailsConverter;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsConverter.toDto(userRepository.findUserByUserName(username));
    }
}
