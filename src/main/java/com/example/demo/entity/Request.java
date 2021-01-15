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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Builder
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private RequestType type;

    @Column(name = "CREATION_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "HANDLE_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate handleDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "CREATOR")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "RECEIVER")
    private User receiver;

}
