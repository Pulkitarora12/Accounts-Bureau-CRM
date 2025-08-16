package com.example.SalesManagementSoftware.repository;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SalesManagementSoftware.entity.VisitRecord;

@Repository
public interface  DailyVisitRepo extends org.springframework.data.jpa.repository.JpaRepository<VisitRecord, Long> {

    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE DATE(v.dateFilled) = :today")
    long countReportsSubmittedToday(@Param("today") Date today);

    // Count reports with tally check true today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.tally = true AND DATE(v.dateFilled) = :today")
    long countReportsWithTallyCheck(@Param("today") Date today);

    // Count reports with agreed for demo true today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.agreedForDemo = true AND DATE(v.dateFilled) = :today")
    long countReportsAgreedForDemo(@Param("today") Date today);

    // Count reports with new license today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.opportunity = 'New License' AND DATE(v.dateFilled) = :today")
    long countReportsWithNewLicense(@Param("today") Date today);


    
}
