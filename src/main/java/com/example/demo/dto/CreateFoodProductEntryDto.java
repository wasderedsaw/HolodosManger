package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Builder
@ToString
@EqualsAndHashCode
@Value
@AllArgsConstructor
public class CreateFoodProductEntryDto {
    Long amount;
    LocalDate productionDate;
    Long typeId;
    Long fridgeId;
}
