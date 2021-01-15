package com.example.demo.converter;

import com.example.demo.dto.CreateRequestDto;
import com.example.demo.dto.ViewRequestDto;
import com.example.demo.entity.Request;

public interface RequestConverter {
    Request fromDto(CreateRequestDto createRequestDto);
    ViewRequestDto toDto(Request request);
}
