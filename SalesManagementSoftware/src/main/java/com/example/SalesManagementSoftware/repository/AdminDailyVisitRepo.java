package com.example.SalesManagementSoftware.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SalesManagementSoftware.entity.DailyRecord;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.entity.VisitRecord;

@Repository
public interface AdminDailyVisitRepo extends JpaRepository<DailyRecord, Long> {
    
    Optional<DailyRecord> findByScoutNameAndDateFilled(Employee scoutName, LocalDate dateFilled);

    @Query("SELECT d FROM DailyRecord d WHERE d.scoutName = :employee AND d.dateFilled = :date")
    Page<DailyRecord> findByEmployeeAndDate(
            @Param("employee") Employee employee,
            @Param("date") LocalDate date,
            Pageable pageable
    );

    @Query("SELECT d FROM DailyRecord d WHERE d.dateFilled = :date")
    Page<DailyRecord> findAllByDate(
            @Param("date") LocalDate date,
            Pageable pageable
    );

}
