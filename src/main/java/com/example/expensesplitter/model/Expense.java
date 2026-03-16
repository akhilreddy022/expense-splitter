package com.example.expensesplitter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private String description;
    private Double amount;
    private String paidBy;
    private String splitAmong;
    private String category;
    private String notes;
    private String createdAt;
}
