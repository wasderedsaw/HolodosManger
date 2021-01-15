package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FRIDGE")
@Builder
@ToString(exclude = {"membership"})
@EqualsAndHashCode(exclude = {"membership"})
public class Fridge {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "USER_AMOUNT")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "MANAGER")
    private User owner;

    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Membership> membership = new ArrayList<>();
}
