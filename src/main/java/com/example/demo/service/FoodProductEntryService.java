package com.example.demo.service;

import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;

public interface FoodProductEntryService {
    FoodProductEntry createFoodProductEntry(CreateFoodProductEntryDto createFoodProductEntryDto);
    FoodProductEntry getFoodProductEntryById(Long id);
    FoodProductEntry increaseFoodProductEntryAmount(Long id, Long increment);
    FoodProductEntry decreaseFoodProductEntryAmount(Long id, Long decrement);
}
