package com.example.SalesManagementSoftware.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expenses {

    @Id
    private String ExpenseId;
    
    @ManyToOne
    private Employee employee;
}
