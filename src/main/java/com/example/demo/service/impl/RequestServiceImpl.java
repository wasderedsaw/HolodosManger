package com.example.demo.service.impl;

import com.example.demo.converter.RequestConverter;
import com.example.demo.dto.CreateRequestDto;
import com.example.demo.entity.Request;
import com.example.demo.entity.Status;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.RequestRepository;
import com.example.demo.service.RequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestConverter requestConverter;
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestConverter requestConverter, RequestRepository requestRepository) {
        this.requestConverter = requestConverter;
        this.requestRepository = requestRepository;
    }

    @Override
    public Request createRequest(CreateRequestDto createRequestDto) {
        return requestRepository.save(requestConverter.fromDto(createRequestDto));
    }

    @Override
    public Request getRequestById(Long id) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request == null) {
            throw new EntityNotFoundException();
        }
        return request;
    }

    @Override
    public void updateRequestStatus(Long id, Status status) {
        Request request = getRequestById(id);
        request.setStatus(status);
        request.setHandleDate(LocalDate.now());
        requestRepository.save(request);
    }
}
