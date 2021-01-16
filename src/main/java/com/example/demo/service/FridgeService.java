package com.example.demo.service;

import com.example.demo.dto.CreateFridgeDto;
import com.example.demo.entity.Fridge;
import org.springframework.security.access.prepost.PreAuthorize;

public interface FridgeService {
    @PreAuthorize("hasAnyAuthority('PRIVILEGED_USER', 'ADMIN')")
    Fridge createFridge(CreateFridgeDto createFridgeDto);

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    Fridge getFridgeById(Long id);
}
