package com.example.SalesManagementSoftware.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scoutName;

    private String placeOfVisit;

    private String companyName;

    private String newOrRevisit;

    private String contactPersonName;

    private String contactNumber;

    private String natureOfBusiness;

    private Boolean computer;

    private Boolean tally;

    @Temporal(TemporalType.DATE)
    private Date lastUpgrade;

    private String opportunity; 
    // e.g. None/New license/Upgrade/TSS/Service/Customization

    private Boolean revisitRequired;

    private Boolean agreedForDemo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFilled;

    @PrePersist
    protected void onCreate() {
        dateFilled = new Date();
    }

    @ManyToOne
    private Employee employee;
}
