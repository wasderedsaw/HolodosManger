package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Builder
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodProductEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "PRODUCTION_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate productionDate;

    @ManyToOne
    @JoinColumn(name = "TYPE")
    private FoodProductType type;

    @ManyToOne
    @JoinColumn(name = "FRIDGE")
    private Fridge fridge;

}
