package com.example.demo.service;

import com.example.demo.dto.CreateRequestDto;
import com.example.demo.entity.Request;
import com.example.demo.entity.Status;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RequestService {

    @PreAuthorize("hasAnyAuthority('USER', 'PRIVILEGED_USER', 'ADMIN')")
    Request createRequest(CreateRequestDto createRequestDto);

    Request getRequestById(Long id);

    @PreAuthorize("hasAnyAuthority('PRIVILEGED_USER', 'ADMIN')")
    void updateRequestStatus(Long id, Status status);
}
