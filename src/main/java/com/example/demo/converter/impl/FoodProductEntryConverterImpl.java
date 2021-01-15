package com.example.demo.converter.impl;

import com.example.demo.converter.FoodProductEntryConverter;
import com.example.demo.converter.FoodProductTypeConverter;
import com.example.demo.converter.FridgeConverter;
import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.dto.ViewFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;
import com.example.demo.service.FoodProductTypeService;
import com.example.demo.service.FridgeService;
import org.springframework.stereotype.Component;

@Component
public class FoodProductEntryConverterImpl implements FoodProductEntryConverter {

    private final FoodProductTypeService foodProductTypeService;
    private final FridgeService fridgeService;
    private final FoodProductTypeConverter foodProductTypeConverter;
    private final FridgeConverter fridgeConverter;

    public FoodProductEntryConverterImpl(FoodProductTypeService foodProductTypeService, FridgeService fridgeService,
                                         FoodProductTypeConverter foodProductTypeConverter, FridgeConverter fridgeConverter) {
        this.foodProductTypeService = foodProductTypeService;
        this.fridgeService = fridgeService;
        this.foodProductTypeConverter = foodProductTypeConverter;
        this.fridgeConverter = fridgeConverter;
    }

    @Override
    public FoodProductEntry fromDto(CreateFoodProductEntryDto createFoodProductEntryDto) {
        return FoodProductEntry.builder()
                .id(null)
                .amount(createFoodProductEntryDto.getAmount())
                .productionDate(createFoodProductEntryDto.getProductionDate())
                .type(foodProductTypeService.getFoodProductTypeById(createFoodProductEntryDto.getTypeId()))
                .fridge(fridgeService.getFridgeById(createFoodProductEntryDto.getFridgeId()))
                .build();
    }

    @Override
    public ViewFoodProductEntryDto toDto(FoodProductEntry foodProductEntry) {
        return ViewFoodProductEntryDto.builder()
                .amount(foodProductEntry.getAmount())
                .productionDate(foodProductEntry.getProductionDate())
                .type(foodProductTypeConverter.toDto(foodProductEntry.getType()))
                .fridge(fridgeConverter.toDto(foodProductEntry.getFridge()))
                .build();
    }
}
