package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.io.File;

@Builder
@ToString
@EqualsAndHashCode
@Value
@AllArgsConstructor
public class ViewFoodProductTypeDto {
    String name;
    String MeasurementUnits;
    Long duration;
    String picture;
}
