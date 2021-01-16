package com.example.demo.service;

import com.example.demo.model.JwtAuthenticationResponse;
import com.example.demo.model.LoginRequest;

public interface AuthenticationService {
    
    JwtAuthenticationResponse createAuthenticationToken(LoginRequest loginRequest);
}
