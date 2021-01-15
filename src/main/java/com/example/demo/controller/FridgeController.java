package com.example.demo.controller;

import com.example.demo.converter.FridgeConverter;
import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.dto.ViewFridgeDto;
import com.example.demo.service.FridgeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fridge")
public class FridgeController {

    private final FridgeService fridgeService;
    private final FridgeConverter fridgeConverter;

    public FridgeController(FridgeService fridgeService, FridgeConverter fridgeConverter) {
        this.fridgeService = fridgeService;
        this.fridgeConverter = fridgeConverter;
    }

    @PostMapping("/create")
    public ViewFridgeDto createFridge(@RequestBody CreateFridgeDto createFridgeDto) {
        return fridgeConverter.toDto(fridgeService.createFridge(createFridgeDto));
    }

    @GetMapping("/{id}")
    public ViewFridgeDto getFridgeById(@PathVariable Long id) {
        return fridgeConverter.toDto(fridgeService.getFridgeById(id));
    }
}
