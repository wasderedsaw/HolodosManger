package com.example.demo.controller;

import com.example.demo.converter.RequestConverter;
import com.example.demo.dto.CreateRequestDto;
import com.example.demo.dto.ViewRequestDto;
import com.example.demo.entity.Request;
import com.example.demo.entity.Status;
import com.example.demo.service.RequestService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;
    private final RequestConverter requestConverter;

    public RequestController(RequestService requestService, RequestConverter requestConverter) {
        this.requestService = requestService;
        this.requestConverter = requestConverter;
    }

    @PostMapping("/create")
    public ViewRequestDto createRequest(@RequestBody CreateRequestDto createRequestDto) {
        return requestConverter.toDto(requestService.createRequest(createRequestDto));
    }

    @GetMapping("/{id}")
    public ViewRequestDto getRequestById(@PathVariable Long id) {
        return requestConverter.toDto(requestService.getRequestById(id));
    }

    @PatchMapping("/{id}")
    public void updateRequestStatusById(@PathVariable Long id, @Param("status") Status status) {
        requestService.updateRequestStatus(id, status);
    }

}
