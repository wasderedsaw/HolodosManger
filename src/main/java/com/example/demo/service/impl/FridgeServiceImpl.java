package com.example.demo.service.impl;

import com.example.demo.converter.FridgeConverter;
import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.entity.Fridge;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.FridgeRepository;
import com.example.demo.service.FridgeService;
import org.springframework.stereotype.Service;

@Service
public class FridgeServiceImpl implements FridgeService {

    private final FridgeRepository fridgeRepository;
    private final FridgeConverter fridgeConverter;

    public FridgeServiceImpl(FridgeRepository fridgeRepository, FridgeConverter fridgeConverter) {
        this.fridgeRepository = fridgeRepository;
        this.fridgeConverter = fridgeConverter;
    }

    @Override
    public Fridge createFridge(CreateFridgeDto createFridgeDto) {
        return fridgeRepository.save(fridgeConverter.fromDto(createFridgeDto));
    }

    @Override
    public Fridge getFridgeById(Long id) {
        Fridge fridge = fridgeRepository.findById(id).orElse(null);
        if (fridge == null) {
            throw new EntityNotFoundException();
        }
        return fridge;
    }
}
