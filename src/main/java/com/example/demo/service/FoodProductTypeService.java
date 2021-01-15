package com.example.demo.service;

import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.entity.FoodProductType;

public interface FoodProductTypeService {
    FoodProductType createFoodProductType(CreateFoodProductTypeDto createFoodProductTypeDto);
    FoodProductType getFoodProductTypeById(Long id);
}
