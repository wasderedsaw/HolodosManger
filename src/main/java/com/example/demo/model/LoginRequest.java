package com.example.demo.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
