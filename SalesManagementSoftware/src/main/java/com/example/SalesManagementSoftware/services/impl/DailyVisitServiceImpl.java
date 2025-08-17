package com.example.SalesManagementSoftware.services.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.SalesManagementSoftware.entity.DailyRecord;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.repository.AdminDailyVisitRepo;
import com.example.SalesManagementSoftware.repository.DailyVisitRepo;
import com.example.SalesManagementSoftware.services.DailyVisitService;

@Service
public class DailyVisitServiceImpl implements DailyVisitService {

    @Autowired
    private DailyVisitRepo repo;

    @Autowired
    private AdminDailyVisitRepo repository;

    public void save(DailyRecord dailyRecord) {
        repository.save(dailyRecord);
    }

    public void update(DailyRecord dailyRecord, LocalDate date) {
        Employee emp = dailyRecord.getScoutName();

        DailyRecord record = repository.findByScoutNameAndDateFilled(emp, date).orElse(null);

        if (record == null) {
            repository.save(dailyRecord);
        } else {
            record.setNoOfCompaniesUsingTally(dailyRecord.getNoOfCompaniesUsingTally());
            record.setNoOfCompaniesVisited(dailyRecord.getNoOfCompaniesVisited());
            record.setNoOfDemoAgreed(dailyRecord.getNoOfDemoAgreed());
            record.setOtherOpportunities(dailyRecord.getOtherOpportunities());
            record.setProspectForNewLicense(dailyRecord.getProspectForNewLicense());

            repository.save(record);
        }
    }

    @Override
    public Long getTotalVisits(LocalDate today, Employee employee) {
        return repo.countReportsSubmittedToday(today, employee);
    }

    @Override
    public Long getTallyChecked(LocalDate today, Employee employee) {
        return repo.countReportsWithTallyCheck(today, employee);
    }

    @Override
    public Long getAgreedForDemo(LocalDate today, Employee employee) {
        return repo.countReportsAgreedForDemo(today, employee);
    }

    @Override
    public Long getNewLicense(LocalDate today, Employee employee) {
        return repo.countReportsWithNewLicense(today, employee);
    }

    @Override
    public Long getOtherOpportunties(LocalDate today, Employee employee) {
        return repo.countReportsWithOtherOpportunities(today, employee);
    }

    @Override
    public Optional<DailyRecord> getDailyRecord(LocalDate date, Employee employee) {
        
        return repository.findByScoutNameAndDateFilled(employee, date);
    }

    @Override
    public Page<DailyRecord> getEmployeeDailyRecord(LocalDate date, Employee employee, String sortBy, String order,
            int page, int size) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return repository.findByEmployeeAndDate(employee, date, pageable);
    }

    @Override
    public Page<DailyRecord> getAllEmployeeDailyRecord(LocalDate date, String sortBy, String order, int page,
            int size) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return repository.findAllByDate(date, pageable);
    }

    // @Override
    // public Long getAllTotalVisits(Date today) {
    //     return repo.countAllReportsSubmittedToday(today);
    // }

    // @Override
    // public Long getAllTallyChecked(Date today) {
    //     return repo.countAllReportsWithTallyCheck(today);
    // }

    // @Override
    // public Long getAllAgreedForDemo(Date today) {
    //     return repo.countAllReportsAgreedForDemo(today);
    // }

    // @Override
    // public Long getAllNewLicense(Date today) {
    //     return repo.countAllReportsWithNewLicense(today);
    // }

    // @Override
    // public Long getAllOtherOpportunties(Date today) {
    //     return repo.countAllReportsWithOtherOpportunities(today);
    // }
    
}
