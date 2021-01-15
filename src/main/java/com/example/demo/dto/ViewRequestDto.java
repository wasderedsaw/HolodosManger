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
public class ViewRequestDto {
    Long id;
    String type;
    LocalDate creationDate;
    LocalDate handleDate;
    String status;
    ViewUserDto creator;
}
