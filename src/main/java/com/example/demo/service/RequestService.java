package com.example.demo.service;

import com.example.demo.dto.CreateRequestDto;
import com.example.demo.entity.Request;
import com.example.demo.entity.Status;

public interface RequestService {
    Request createRequest(CreateRequestDto createRequestDto);
    Request getRequestById(Long id);
    void updateRequestStatus(Long id, Status status);
}
