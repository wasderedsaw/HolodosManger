package com.example.demo.service;

import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;
import org.springframework.security.access.prepost.PreAuthorize;

public interface FoodProductEntryService {

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    FoodProductEntry createFoodProductEntry(CreateFoodProductEntryDto createFoodProductEntryDto);

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    FoodProductEntry getFoodProductEntryById(Long id);

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    FoodProductEntry increaseFoodProductEntryAmount(Long id, Long increment);

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    FoodProductEntry decreaseFoodProductEntryAmount(Long id, Long decrement);
}
