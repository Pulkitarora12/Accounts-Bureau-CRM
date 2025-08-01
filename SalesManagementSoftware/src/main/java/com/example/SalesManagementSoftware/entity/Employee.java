package com.example.SalesManagementSoftware.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    
    @Id
    private String employeeId; 

    @Column(name = "employee_name", nullable = false, length = 255)
    private String name;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(columnDefinition = "TEXT")
    private String profileLink;

    @Column(length = 20)
    private String phoneNumber;

    // Sales-specific fields
    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String designation;

    private boolean enabled = false; 
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value=EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerURL;

    // Changed from contacts to dailyReports
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reports> reports = new ArrayList<>();

    // Add expenses relationship
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Expenses> expenses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.EMPLOYEE; 

    // Helper methods
    public boolean hasRole(Role checkRole) {
        return this.role == checkRole;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }

    public boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }

}
