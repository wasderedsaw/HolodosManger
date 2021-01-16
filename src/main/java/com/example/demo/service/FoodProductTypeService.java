package com.example.demo.service;

import com.example.demo.dto.CreateFoodProductTypeDto;
import com.example.demo.entity.FoodProductType;
import org.springframework.security.access.prepost.PreAuthorize;

public interface FoodProductTypeService {

    @PreAuthorize("hasAuthority('ADMIN')")
    FoodProductType createFoodProductType(CreateFoodProductTypeDto createFoodProductTypeDto);

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    FoodProductType getFoodProductTypeById(Long id);
}
