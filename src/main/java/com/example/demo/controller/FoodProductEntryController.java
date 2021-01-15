package com.example.demo.controller;

import com.example.demo.converter.FoodProductEntryConverter;
import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.dto.ViewFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;
import com.example.demo.service.FoodProductEntryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodProductEntry")
public class FoodProductEntryController {

    private final FoodProductEntryService foodProductEntryService;
    private final FoodProductEntryConverter foodProductEntryConverter;

    public FoodProductEntryController(FoodProductEntryService foodProductEntryService, FoodProductEntryConverter foodProductEntryConverter) {
        this.foodProductEntryService = foodProductEntryService;
        this.foodProductEntryConverter = foodProductEntryConverter;
    }

    @PostMapping("/create")
    public ViewFoodProductEntryDto createFoodProductEntry(@RequestBody CreateFoodProductEntryDto createFoodProductEntryDto) {
        return foodProductEntryConverter.toDto(foodProductEntryService.createFoodProductEntry(createFoodProductEntryDto));
    }

    @GetMapping("/{id}")
    public ViewFoodProductEntryDto getFoodProductEntryById(@PathVariable Long id) {
        return foodProductEntryConverter.toDto(foodProductEntryService.getFoodProductEntryById(id));
    }

    @PatchMapping("/increase")
    public ViewFoodProductEntryDto increaseFoodProductEntryAmount(@RequestParam Long id, @RequestParam Long increment) {
        return foodProductEntryConverter.toDto(foodProductEntryService.increaseFoodProductEntryAmount(id, increment));
    }

    @PatchMapping("/decrease")
    public ViewFoodProductEntryDto decreaseFoodProductEntryAmount(@RequestParam Long id, @RequestParam Long decrement) {
        FoodProductEntry foodProductEntry = foodProductEntryService.decreaseFoodProductEntryAmount(id, decrement);
        if (foodProductEntry == null) {
            return null;
        }
        return foodProductEntryConverter.toDto(foodProductEntry);
    }
}
