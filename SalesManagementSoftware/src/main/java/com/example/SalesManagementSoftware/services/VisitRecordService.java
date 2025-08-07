package com.example.SalesManagementSoftware.services;

import com.example.SalesManagementSoftware.entity.VisitRecord;

import java.util.*;

public interface VisitRecordService {

    VisitRecord save(VisitRecord visitRecord);

    VisitRecord update(VisitRecord visitRecord);

    List<VisitRecord> getAll();

    VisitRecord getById(Long id);

    void delete(Long id);

    List<VisitRecord> getByUserId(Long userId);


}