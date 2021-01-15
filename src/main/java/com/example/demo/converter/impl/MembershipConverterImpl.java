package com.example.demo.converter.impl;

import com.example.demo.converter.FridgeConverter;
import com.example.demo.converter.MembershipConverter;
import com.example.demo.converter.UserConverter;
import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.dto.ViewMembershipDto;
import com.example.demo.entity.FridgeRoles;
import com.example.demo.entity.Membership;
import com.example.demo.service.FridgeService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class MembershipConverterImpl implements MembershipConverter {

    private final UserService userService;
    private final FridgeService fridgeService;
    private final UserConverter userConverter;
    private final FridgeConverter fridgeConverter;

    public MembershipConverterImpl(UserService userService, FridgeService fridgeService, UserConverter userConverter, FridgeConverter fridgeConverter) {
        this.userService = userService;
        this.fridgeService = fridgeService;
        this.userConverter = userConverter;
        this.fridgeConverter = fridgeConverter;
    }

    @Override
    public Membership fromDto(CreateMembershipDto createMemberShipDto) {
        return Membership.builder()
                .id(null)
                .user(userService.getUserById(createMemberShipDto.getUserId()))
                .fridge(fridgeService.getFridgeById(createMemberShipDto.getFridgeId()))
                .fridgeRole(FridgeRoles.valueOf(createMemberShipDto.getFridgeRole()))
                .build();
    }

    @Override
    public ViewMembershipDto toDto(Membership membership) {
        return ViewMembershipDto.builder()
                .user(userConverter.toDto(membership.getUser()))
                .fridge(fridgeConverter.toDto(membership.getFridge()))
                .fridgeRole(membership.getFridgeRole().toString())
                .build();
    }
}
