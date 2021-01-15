package com.example.demo.service.impl;

import com.example.demo.converter.FoodProductEntryConverter;
import com.example.demo.dto.CreateFoodProductEntryDto;
import com.example.demo.entity.FoodProductEntry;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.TakingTooMuchFoodException;
import com.example.demo.repository.FoodProductEntryRepository;
import com.example.demo.service.FoodProductEntryService;
import org.springframework.stereotype.Service;

@Service
public class FoodProductEntryServiceImpl implements FoodProductEntryService {

    private final FoodProductEntryConverter foodProductEntryConverter;
    private final FoodProductEntryRepository foodProductEntryRepository;

    public FoodProductEntryServiceImpl(FoodProductEntryConverter foodProductEntryConverter, FoodProductEntryRepository foodProductEntryRepository) {
        this.foodProductEntryConverter = foodProductEntryConverter;
        this.foodProductEntryRepository = foodProductEntryRepository;
    }

    @Override
    public FoodProductEntry createFoodProductEntry(CreateFoodProductEntryDto createFoodProductEntryDto) {
        return foodProductEntryRepository.save(foodProductEntryConverter.fromDto(createFoodProductEntryDto));
    }

    @Override
    public FoodProductEntry getFoodProductEntryById(Long id) {
        FoodProductEntry foodProductEntry = foodProductEntryRepository.findById(id).orElse(null);
        if (foodProductEntry == null) {
            throw new EntityNotFoundException();
        }
        return foodProductEntry;
    }

    @Override
    public FoodProductEntry increaseFoodProductEntryAmount(Long id, Long increment) {
        FoodProductEntry foodProductEntry = getFoodProductEntryById(id);
        foodProductEntry.setAmount(foodProductEntry.getAmount() + increment);
        return foodProductEntryRepository.save(foodProductEntry);
    }

    @Override
    public FoodProductEntry decreaseFoodProductEntryAmount(Long id, Long decrement) {
        FoodProductEntry foodProductEntry = getFoodProductEntryById(id);
        if (foodProductEntry.getAmount() < decrement) {
            throw new TakingTooMuchFoodException();
        }
        foodProductEntry.setAmount(foodProductEntry.getAmount() - decrement);
        if (foodProductEntry.getAmount() == 0) {
            foodProductEntryRepository.delete(foodProductEntry);
            return null;
        } else {
            return foodProductEntryRepository.save(foodProductEntry);
        }
    }
}
