package com.example.demo.service;

import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.entity.Membership;

public interface MembershipService {
    Membership createMembership(CreateMembershipDto createMemberShipDto);
    Membership getMembershipById(Long id);

}
