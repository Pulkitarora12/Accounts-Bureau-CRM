package com.example.SalesManagementSoftware.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DailyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scoutName;

    private int noOfCompaniesVisited;

    private int noOfCompaniesUsingTally;
   
    private int noOfDemoAgreed;
   
    private int otherOpportunities;

    private LocalDate dateFilled; // use LocalDate for easy filtering
}