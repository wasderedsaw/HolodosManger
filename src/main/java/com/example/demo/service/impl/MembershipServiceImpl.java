package com.example.demo.service.impl;

import com.example.demo.converter.MembershipConverter;
import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.entity.Membership;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.MembershipRepository;
import com.example.demo.service.MembershipService;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipConverter membershipConverter;

    public MembershipServiceImpl(MembershipRepository membershipRepository, MembershipConverter membershipConverter) {
        this.membershipRepository = membershipRepository;
        this.membershipConverter = membershipConverter;
    }

    @Override
    public Membership createMembership(CreateMembershipDto createMemberShipDto) {
        return membershipRepository.save(membershipConverter.fromDto(createMemberShipDto));
    }

    @Override
    public Membership getMembershipById(Long id) {
        Membership membership = membershipRepository.findById(id).orElse(null);
        if (membership == null) {
            throw new EntityNotFoundException();
        }
        return membership;
    }
}
