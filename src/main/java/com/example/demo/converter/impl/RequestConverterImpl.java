package com.example.demo.converter.impl;

import com.example.demo.converter.RequestConverter;
import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateRequestDto;
import com.example.demo.dto.ViewRequestDto;
import com.example.demo.entity.Request;
import com.example.demo.entity.RequestType;
import com.example.demo.entity.Status;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestConverterImpl implements RequestConverter {

    private final UserService userService;
    private final UserConverter userConverter;

    public RequestConverterImpl(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public Request fromDto(CreateRequestDto createRequestDto) {
        return Request.builder()
                .id(null)
                .creator(userService.getUserById(createRequestDto.getCreatorId()))
                .receiver(userService.getUserById(createRequestDto.getReceiverId()))
                .type(RequestType.valueOf(createRequestDto.getRequestType()))
                .status(Status.OPEN)
                .creationDate(LocalDate.now())
                .build();
    }

    @Override
    public ViewRequestDto toDto(Request request) {
        return ViewRequestDto.builder()
                .id(request.getId())
                .type(request.getType().toString())
                .creationDate(request.getCreationDate())
                .handleDate(request.getHandleDate())
                .status(request.getStatus().toString())
                .creator(userConverter.toDto(request.getCreator()))
                .build();
    }
}
