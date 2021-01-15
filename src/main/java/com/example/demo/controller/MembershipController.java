package com.example.demo.controller;

import com.example.demo.converter.MembershipConverter;
import com.example.demo.dto.CreateMembershipDto;
import com.example.demo.dto.ViewMembershipDto;
import com.example.demo.entity.Membership;
import com.example.demo.service.MembershipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;
    private final MembershipConverter membershipConverter;

    public MembershipController(MembershipService membershipService, MembershipConverter membershipConverter) {
        this.membershipService = membershipService;
        this.membershipConverter = membershipConverter;
    }

    @PostMapping("/create")
    public ViewMembershipDto createMembership(@RequestBody CreateMembershipDto createMemberShipDto) {
        return membershipConverter.toDto(membershipService.createMembership(createMemberShipDto));
    }

    @GetMapping("/{id}")
    public ViewMembershipDto getMembershipById(@PathVariable Long id) {
        return membershipConverter.toDto(membershipService.getMembershipById(id));
    }
}
