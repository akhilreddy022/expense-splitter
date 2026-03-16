package com.example.expensesplitter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_group")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String emoji;
    private String members;
    private String createdAt;
}