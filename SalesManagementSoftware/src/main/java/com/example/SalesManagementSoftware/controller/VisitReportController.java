package com.example.SalesManagementSoftware.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.example.SalesManagementSoftware.Helper.Helper;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.entity.VisitRecord;
import com.example.SalesManagementSoftware.forms.VisitRecordForm;
import com.example.SalesManagementSoftware.services.UserService;
import com.example.SalesManagementSoftware.services.VisitRecordService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/visit")
public class VisitReportController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitRecordService service;

    private static final Logger logger = LoggerFactory.getLogger(VisitReportController.class);

    @GetMapping("/add")
    public String addReport(Model model, HttpSession session) {
        model.addAttribute("form", new VisitRecordForm());
        String message = (String) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        return "user/addVisitRecord";
    }

    @PostMapping("/add")
    public String saveReport(@ModelAttribute @Valid VisitRecordForm form,
                             BindingResult result,
                             Authentication authentication,
                             HttpSession session,
                             Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.info("Validation error: {}", error.toString()));
            model.addAttribute("form", form);
            model.addAttribute("message", "Please resolve the errors");
            return "user/addVisitRecord";
        }

        try {
            String email = Helper.getEmailOfLoggedInUser(authentication);
            Employee user = userService.getUserByEmail(email);
            if (user == null) {
                model.addAttribute("form", form);
                model.addAttribute("message", "User not found. Please log in again.");
                return "user/addVisitRecord";
            }

            VisitRecord record = new VisitRecord();
            record.setScoutName(form.getScoutName());
            record.setPlaceOfVisit(form.getPlaceOfVisit());
            record.setCompanyName(form.getCompanyName());
            record.setNewOrRevisit(form.getNewOrRevisit());
            record.setContactPersonName(form.getContactPersonName());
            record.setContactNumber(form.getContactNumber());
            record.setNatureOfBusiness(form.getNatureOfBusiness());
            record.setComputer(form.isComputer());
            record.setTally(form.isTally());
            record.setLastUpgrade(form.getLastUpgrade());
            record.setOpportunity(form.getOpportunity());
            record.setRevisitRequired(form.isRevisitRequired());
            record.setAgreedForDemo(form.isAgreedForDemo());
            record.setEmployee(user);

            service.save(record);
            session.setAttribute("message", "You have successfully added a new visit record");
            return "redirect:/user/visit/add"; // Redirect to avoid form resubmission
        } catch (Exception e) {
            logger.error("Error saving visit record", e);
            model.addAttribute("form", form);
            model.addAttribute("message", "An error occurred while saving the visit record. Please try again.");
            return "user/addVisitRecord";
        }
    }
}