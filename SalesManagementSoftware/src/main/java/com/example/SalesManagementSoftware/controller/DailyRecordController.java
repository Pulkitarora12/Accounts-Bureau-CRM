package com.example.SalesManagementSoftware.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SalesManagementSoftware.Helper.Helper;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.services.DailyVisitService;
import com.example.SalesManagementSoftware.services.UserService;
import com.example.SalesManagementSoftware.services.VisitRecordService;

@Controller
@RequestMapping("/user/visit")
public class DailyRecordController {

    @Autowired
    private DailyVisitService service;

    @GetMapping("/dailyReports")
    public String getReportsCount(Model model) {

        // Always take today's date
        Date date = new Date();

        Long totalVisits = service.getTotalVisits(date);
        System.out.println(totalVisits);
        Long totalAgreedToDemo = service.getAgreedForDemo(date);
        System.out.println(totalAgreedToDemo);
        Long totalNewLicense = service.getNewLicense(date);
        System.out.println(totalNewLicense);
        Long totalTally = service.getTallyChecked(date);
        System.out.println(totalTally);

        // Add to model
        model.addAttribute("counts", totalVisits);
        model.addAttribute("demo", totalAgreedToDemo);
        model.addAttribute("newLicense", totalNewLicense);
        model.addAttribute("tally", totalTally);
        model.addAttribute("selectedDate", date);

        return "user/dailyReports";
    }

}
