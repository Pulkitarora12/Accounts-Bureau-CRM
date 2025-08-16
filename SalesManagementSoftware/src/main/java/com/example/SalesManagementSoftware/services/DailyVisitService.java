package com.example.SalesManagementSoftware.services;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.SalesManagementSoftware.repository.DailyVisitRepo;

public interface DailyVisitService {

    Long getTotalVisits(Date today);

    Long getTallyChecked(Date today);

    Long getAgreedForDemo(Date today);

    Long getNewLicense(Date today);

}
