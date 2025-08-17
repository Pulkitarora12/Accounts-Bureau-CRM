package com.example.SalesManagementSoftware.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SalesManagementSoftware.Helper.AppConstants;
import com.example.SalesManagementSoftware.Helper.Helper;
import com.example.SalesManagementSoftware.entity.DailyRecord;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.services.DailyVisitService;
import com.example.SalesManagementSoftware.services.UserService;

@Controller
@RequestMapping("/user/visit")
public class DailyRecordController {

    @Autowired
    private DailyVisitService service;

    @Autowired
    private UserService userService;

    @GetMapping("/dailyReports")
    public String getReportsCount(Model model, Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "" + AppConstants.PAGE_SIZE) int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        // Always take today's date
        LocalDate date = LocalDate.now();

        String email = Helper.getEmailOfLoggedInUser(authentication);
        Employee user = userService.getUserByEmail(email);

        if (!user.isEnabled()) {
            return "login";
        }

        Long totalVisits = service.getTotalVisits(date, user);
        System.out.println(totalVisits);
        Long totalAgreedToDemo = service.getAgreedForDemo(date, user);
        System.out.println(totalAgreedToDemo);
        Long totalNewLicense = service.getNewLicense(date, user);
        System.out.println(totalNewLicense);
        Long totalTally = service.getTallyChecked(date, user);
        System.out.println(totalTally);
        Long others = service.getOtherOpportunties(date, user);
        System.out.println(others);

        DailyRecord record = new DailyRecord();
        record.setScoutName(user);
        record.setDateFilled(date);
        record.setNoOfCompaniesUsingTally(totalTally);
        record.setNoOfCompaniesVisited(totalVisits);
        record.setNoOfDemoAgreed(totalAgreedToDemo);
        record.setProspectForNewLicense(totalNewLicense);
        record.setOtherOpportunities(others);

        service.update(record, date);

        // // Add to model
        // model.addAttribute("counts", totalVisits);
        // model.addAttribute("demo", totalAgreedToDemo);
        // model.addAttribute("newLicense", totalNewLicense);
        // model.addAttribute("tally", totalTally);
        // model.addAttribute("selectedDate", date);
        // model.addAttribute("others", others);

        Page<DailyRecord> pageRecord = service.getEmployeeDailyRecord(date, user, sortBy, direction, page, size);

        model.addAttribute("pageRecord", pageRecord);

        return "user/dailyReports";
    }

    @GetMapping("/allDailyReports")
    public String getAllReportsCount(
            Model model,
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "" + AppConstants.PAGE_SIZE) int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        // Always take today's date
        LocalDate date = LocalDate.now();

        String email = Helper.getEmailOfLoggedInUser(authentication);
        Employee user = userService.getUserByEmail(email);

        if (!user.isEnabled()) {
            return "login";
        }

        Page<DailyRecord> pageRecord = service.getAllEmployeeDailyRecord(date, sortBy, direction, page, size);

        model.addAttribute("pageRecord", pageRecord);
        
        return "user/AllDailyReports";
    }

}
