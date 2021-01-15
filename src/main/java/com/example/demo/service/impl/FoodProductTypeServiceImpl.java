package com.example.demo.service.impl;

import com.example.demo.converter.FoodProductTypeConverter;
import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.entity.FoodProductType;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.FoodProductTypeRepository;
import com.example.demo.service.FoodProductTypeService;
import org.springframework.stereotype.Service;

@Service
public class FoodProductTypeServiceImpl implements FoodProductTypeService {

    private final FoodProductTypeConverter foodProductTypeConverter;
    private final FoodProductTypeRepository foodProductTypeRepository;

    public FoodProductTypeServiceImpl(FoodProductTypeConverter foodProductTypeConverter, FoodProductTypeRepository foodProductTypeRepository) {
        this.foodProductTypeConverter = foodProductTypeConverter;
        this.foodProductTypeRepository = foodProductTypeRepository;
    }

    @Override
    public FoodProductType createFoodProductType(CreateFoodProductTypeDto createFoodProductTypeDto) {
        return foodProductTypeRepository.save(foodProductTypeConverter.fromDto(createFoodProductTypeDto));
    }

    @Override
    public FoodProductType getFoodProductTypeById(Long id) {
        FoodProductType foodProductType = foodProductTypeRepository.findById(id).orElse(null);
        if (foodProductType == null) {
            throw new EntityNotFoundException();
        }
        return foodProductType;
    }
}
