package com.example.demo.converter;

import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.dto.ViewFridgeDto;
import com.example.demo.entity.Fridge;

public interface FridgeConverter {
    Fridge fromDto(CreateFridgeDto createFridgeDto);
    ViewFridgeDto toDto(Fridge fridge);
}
