package com.example.SalesManagementSoftware.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reports {

    @Id
    private String reportId;

    @ManyToOne
    private Employee employee;
    
}
 