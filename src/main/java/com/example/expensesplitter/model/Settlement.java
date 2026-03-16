package com.example.expensesplitter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private String fromMember;
    private String toMember;
    private Double amount;
    private Boolean paid;
    private String paidAt;
}