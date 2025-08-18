package com.example.SalesManagementSoftware.repository;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SalesManagementSoftware.entity.*;


@Repository
public interface  DailyVisitRepo extends org.springframework.data.jpa.repository.JpaRepository<VisitRecord, Long> {

    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE DATE(v.dateFilled) = :today AND v.employee = :employee")
    long countReportsSubmittedToday(@Param("today") LocalDate today, Employee employee);

    // Count reports with tally check true today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.tally = true AND DATE(v.dateFilled) = :today AND v.employee = :employee")
    long countReportsWithTallyCheck(@Param("today") LocalDate today, Employee employee);

    // Count reports with agreed for demo true today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.agreedForDemo = true AND DATE(v.dateFilled) = :today AND v.employee = :employee")
    long countReportsAgreedForDemo(@Param("today") LocalDate today, Employee employee);

    // Count reports with new license today
    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.opportunity = 'New License' AND DATE(v.dateFilled) = :today AND v.employee = :employee")
    long countReportsWithNewLicense(@Param("today") LocalDate today, Employee employee);

    @Query("SELECT COUNT(v) FROM VisitRecord v WHERE ((v.opportunity = 'Other' AND v.otherOpportunities IS NOT NULL AND v.otherOpportunities != '') OR (v.otherOpportunities IS NOT NULL AND v.otherOpportunities != '' AND TRIM(v.otherOpportunities) != '')) AND DATE(v.dateFilled) = :today AND v.employee = :employee")
    long countReportsWithOtherOpportunities(@Param("today") LocalDate today, Employee employee);

    // @Query("SELECT COUNT(v) FROM VisitRecord v WHERE DATE(v.dateFilled) = :today")
    // long countAllReportsSubmittedToday(@Param("today") Date today);

    // // Count reports with tally check true today
    // @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.tally = true AND DATE(v.dateFilled) = :today")
    // long countAllReportsWithTallyCheck(@Param("today") Date today);

    // // Count reports with agreed for demo true today
    // @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.agreedForDemo = true AND DATE(v.dateFilled) = :today")
    // long countAllReportsAgreedForDemo(@Param("today") Date today);

    // // Count reports with new license today
    // @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.opportunity = 'New License' AND DATE(v.dateFilled) = :today")
    // long countAllReportsWithNewLicense(@Param("today") Date today);

    // @Query("SELECT COUNT(v) FROM VisitRecord v WHERE v.customFields['Other Opportunities'] IS NOT NULL AND DATE(v.dateFilled) = :today")
    // long countAllReportsWithOtherOpportunities(@Param("today") Date today);


}
