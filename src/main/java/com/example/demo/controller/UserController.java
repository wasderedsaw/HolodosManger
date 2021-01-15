package com.example.demo.controller;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ViewUserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/create")
    public ViewUserDto createRequest(@RequestBody CreateUserDto createUserDto) {
        return userConverter.toDto(userService.createUser(createUserDto));
    }

    @GetMapping("/{id}")
    public ViewUserDto getUserById(@PathVariable("id") Long id) {
        return userConverter.toDto(userService.getUserById(id));
    }
}
