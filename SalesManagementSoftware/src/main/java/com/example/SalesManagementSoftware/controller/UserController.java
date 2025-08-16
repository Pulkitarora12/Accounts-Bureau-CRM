package com.example.SalesManagementSoftware.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SalesManagementSoftware.Helper.Helper;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

     private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //user profile page
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {

        String email = Helper.getEmailOfLoggedInUser(authentication);
        Employee user = userService.getUserByEmail(email);

        if (user != null) {
            model.addAttribute("loggedInUser", user);
        } else {
            // Handle null case, e.g., redirect to login
            return "redirect:/login";
        }
        
        return "user/profile";
    }
}
