package com.example.SalesManagementSoftware.Helper;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class Helper {
    
     public static String getEmailOfLoggedInUser(Authentication authentication) {

        Logger logger = LogManager.getLogger(Helper.class);
        String email = "";

        //sign in using google or github
        if (authentication instanceof OAuth2AuthenticationToken) {
            

            var token = (OAuth2AuthenticationToken)authentication;
            var registerId = token.getAuthorizedClientRegistrationId();

            DefaultOAuth2User user  = (DefaultOAuth2User)authentication.getPrincipal();
            
            //google
            if (registerId.equals("google")) {

                email = user.getAttribute("email").toString();
                
                System.out.println("Getting email from google");
                // logger.info(user.getAttribute("email").toString());
               
            }

        } else {   //using email and password
            System.out.println("Getting user from local db");
            email = authentication.getName();
        }

        return email;
    }

    public static String getLinkForEmailVerification(String emailToken) {

        String link = "http://localhost:8080/auth/verify-email?token=" + emailToken;

        return link;
    }

}
