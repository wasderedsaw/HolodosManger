package com.example.demo.controller;

import com.example.demo.model.JwtAuthenticationResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        final var response = authenticationService.createAuthenticationToken(loginRequest);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + response.getAccessToken());
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

}
