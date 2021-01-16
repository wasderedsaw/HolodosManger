package com.example.demo.dto;

import com.example.demo.model.JwtAuthenticationResponse;
import lombok.Value;

@Value
public class JwtAuthenticationResponseDto implements JwtAuthenticationResponse {
    String accessToken;
}
