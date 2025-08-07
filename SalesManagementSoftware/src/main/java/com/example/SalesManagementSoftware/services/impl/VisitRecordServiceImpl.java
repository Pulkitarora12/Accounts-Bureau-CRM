package com.example.SalesManagementSoftware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SalesManagementSoftware.Helper.ResourceNotFoundException;
import com.example.SalesManagementSoftware.entity.VisitRecord;
import com.example.SalesManagementSoftware.repository.VisitRecordRepository;
import com.example.SalesManagementSoftware.services.VisitRecordService;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    @Autowired
    private VisitRecordRepository repo;

    @Override
    public VisitRecord save(VisitRecord visitRecord) {
        return repo.save(visitRecord);
    }

    @Override
    public VisitRecord update(VisitRecord visitRecord) {
        return new VisitRecord();
    }

    @Override
    public List<VisitRecord> getAll() {
        return new ArrayList<>();
    }

    @Override
    public VisitRecord getById(Long id) {
        return repo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void delete(Long id) {
        VisitRecord record = repo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found"));
        repo.delete(record);
    }

    @Override
    public List<VisitRecord> getByUserId(Long userId) {
        return repo.findByUserId(userId);
    }


}