package com.example.demo.service;

import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.entity.Membership;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MembershipService {

    @PreAuthorize("hasAnyAuthority('PRIVILEGED_USER', 'ADMIN')")
    Membership createMembership(CreateMembershipDto createMemberShipDto);

}
