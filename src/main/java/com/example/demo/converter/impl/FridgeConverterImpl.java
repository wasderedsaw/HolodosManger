package com.example.demo.converter.impl;

import com.example.demo.converter.FridgeConverter;
import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.dto.ViewFridgeDto;
import com.example.demo.entity.Fridge;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class FridgeConverterImpl implements FridgeConverter {

    private final UserService userService;
    private final UserConverter userConverter;

    public FridgeConverterImpl(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public Fridge fromDto(CreateFridgeDto createFridgeDto) {
        return Fridge.builder()
                .id(null)
                .name(createFridgeDto.getName())
                .amount(1L)
                .owner(userService.getUserById(createFridgeDto.getManagerId()))
                .build();
    }

    @Override
    public ViewFridgeDto toDto(Fridge fridge) {
        return ViewFridgeDto.builder()
                .name(fridge.getName())
                .amount(fridge.getAmount())
                .manager(userConverter.toDto(fridge.getOwner()))
                .build();
    }
}
