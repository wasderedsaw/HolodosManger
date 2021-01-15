package com.example.demo.service;

import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.entity.Fridge;

public interface FridgeService {
    Fridge createFridge(CreateFridgeDto createFridgeDto);
    Fridge getFridgeById(Long id);
}
