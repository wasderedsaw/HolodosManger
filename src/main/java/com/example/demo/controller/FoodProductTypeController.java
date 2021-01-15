package com.example.demo.controller;

import com.example.demo.converter.FoodProductTypeConverter;
import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.dto.ViewFoodProductTypeDto;
import com.example.demo.service.FoodProductTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodProductType")
public class FoodProductTypeController {

    private final FoodProductTypeService foodProductTypeService;
    private final FoodProductTypeConverter foodProductTypeConverter;

    public FoodProductTypeController(FoodProductTypeService foodProductTypeService, FoodProductTypeConverter foodProductTypeConverter) {
        this.foodProductTypeService = foodProductTypeService;
        this.foodProductTypeConverter = foodProductTypeConverter;
    }

    @PostMapping("/create")
    public ViewFoodProductTypeDto createRequest(@RequestBody CreateFoodProductTypeDto createFoodProductTypeDto) {
        return foodProductTypeConverter.toDto(foodProductTypeService.createFoodProductType(createFoodProductTypeDto));
    }

    @GetMapping("/{id}")
    public ViewFoodProductTypeDto getFoodProductTypeById(@PathVariable Long id) {
        return foodProductTypeConverter.toDto(foodProductTypeService.getFoodProductTypeById(id));
    }
}
