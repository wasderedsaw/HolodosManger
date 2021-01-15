package com.example.demo.converter;

import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.dto.ViewFoodProductTypeDto;
import com.example.demo.entity.FoodProductType;

public interface FoodProductTypeConverter {
    FoodProductType fromDto(CreateFoodProductTypeDto createFoodProductTypeDto);
    ViewFoodProductTypeDto toDto(FoodProductType foodProductType);
}
