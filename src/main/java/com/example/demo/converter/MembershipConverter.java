package com.example.demo.converter;

import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.dto.ViewMembershipDto;
import com.example.demo.entity.Membership;

public interface MembershipConverter {
    Membership fromDto(CreateMembershipDto createMemberShipDto);
    ViewMembershipDto toDto(Membership membership);
}
