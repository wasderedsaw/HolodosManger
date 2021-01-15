package com.example.demo.converter;

import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.dto.ViewFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;

public interface FoodProductEntryConverter {
    FoodProductEntry fromDto(CreateFoodProductEntryDto createFoodProductEntryDto);
    ViewFoodProductEntryDto toDto(FoodProductEntry foodProductEntry);
}
