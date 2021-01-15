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
public class ViewFoodProductEntryDto {
    Long amount;
    LocalDate productionDate;
    ViewFoodProductTypeDto type;
    ViewFridgeDto fridge;
}
