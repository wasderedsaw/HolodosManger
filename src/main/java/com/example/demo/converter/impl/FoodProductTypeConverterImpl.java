package com.example.demo.converter.impl;

import com.example.demo.converter.FoodProductTypeConverter;
import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.dto.ViewFoodProductTypeDto;
import com.example.demo.entity.FoodProductType;
import org.springframework.stereotype.Component;

@Component
public class FoodProductTypeConverterImpl implements FoodProductTypeConverter {
    @Override
    public FoodProductType fromDto(CreateFoodProductTypeDto createFoodProductTypeDto) {
        return FoodProductType.builder()
                .id(null)
                .name(createFoodProductTypeDto.getName())
                .measurementUnits(createFoodProductTypeDto.getMeasurementUnits())
                .duration(createFoodProductTypeDto.getDuration())
                .picture(createFoodProductTypeDto.getPicture())
                .build();
    }

    @Override
    public ViewFoodProductTypeDto toDto(FoodProductType foodProductType) {
        return ViewFoodProductTypeDto.builder()
                .name(foodProductType.getName())
                .MeasurementUnits(foodProductType.getMeasurementUnits())
                .duration(foodProductType.getDuration())
                .picture(foodProductType.getPicture())
                .build();
    }
}
