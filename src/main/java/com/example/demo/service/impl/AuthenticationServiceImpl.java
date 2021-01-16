package com.example.demo.service.impl;

import com.example.demo.config.TokenProvider;
import com.example.demo.dto.JwtAuthenticationResponseDto;
import com.example.demo.model.JwtAuthenticationResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponse createAuthenticationToken(LoginRequest loginRequest) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        final String jwt = tokenProvider.createToken(authentication);

        return new JwtAuthenticationResponseDto(jwt);
    }

}
