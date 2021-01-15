package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Builder
@ToString
@EqualsAndHashCode
@Value
@AllArgsConstructor
public class CreateMembershipDto {
    Long userId;
    Long fridgeId;
    String fridgeRole;
}
