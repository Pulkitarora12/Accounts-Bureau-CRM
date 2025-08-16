package com.example.SalesManagementSoftware.services.impl;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SalesManagementSoftware.repository.DailyVisitRepo;
import com.example.SalesManagementSoftware.services.DailyVisitService;

@Service
public class DailyVisitServiceImpl implements DailyVisitService {

    @Autowired
    private DailyVisitRepo repo;

    @Override
    public Long getTotalVisits(Date today) {
        return repo.countReportsSubmittedToday(today);
    }

    @Override
    public Long getTallyChecked(Date today) {
        return repo.countReportsWithTallyCheck(today);
    }

    @Override
    public Long getAgreedForDemo(Date today) {
        return repo.countReportsAgreedForDemo(today);
    }

    @Override
    public Long getNewLicense(Date today) {
        return repo.countReportsWithNewLicense(today);
    }
    
}
