package com.example.SalesManagementSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.example.SalesManagementSoftware.entity.VisitRecord;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    
    @Query("SELECT c FROM VisitRecord c WHERE c.employee.id = :userId")
    List<VisitRecord> findByUserId(@Param("userId") Long userId);
}
