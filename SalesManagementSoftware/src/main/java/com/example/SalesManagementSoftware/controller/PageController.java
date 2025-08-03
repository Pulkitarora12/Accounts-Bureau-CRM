package com.example.SalesManagementSoftware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SalesManagementSoftware.forms.EmployeeForm;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectHome() {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Pulkit Arora");
        model.addAttribute("Github", "https://github.com/Pulkitarora12/Smart-Contact-Manager");
        return "home"; // This will render home.html from templates
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // This will render login.html from templates
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm", employeeForm);
        return "register"; // This will render register.html from templates
    }
}
