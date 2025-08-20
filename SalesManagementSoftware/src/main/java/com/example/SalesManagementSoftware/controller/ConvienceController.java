//package com.example.SalesManagementSoftware.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.SalesManagementSoftware.forms.VisitRecordForm;
//import com.example.SalesManagementSoftware.services.UserService;
//import com.example.SalesManagementSoftware.services.VisitRecordService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/user/convience")
//public class ConvienceController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private VisitRecordService service;
//
//    private static final Logger logger = LoggerFactory.getLogger(VisitReportController.class);
//
//    @GetMapping("/add")
//    public String addReport(Model model, HttpSession session) {
//        model.addAttribute("form", new VisitRecordForm());
//        String message = (String) session.getAttribute("message");
//        if (message != null) {
//            model.addAttribute("message", message);
//            session.removeAttribute("message");
//        }
//        return "user/addVisitRecord"; // Template expects 'loggedInUser' but it's not provided
//    }
//
//    @GetMapping("")
//    public String viewConvience() {
//        return "/user/convience";
//    }
//}
