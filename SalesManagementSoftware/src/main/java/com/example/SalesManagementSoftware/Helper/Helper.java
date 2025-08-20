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
    
    if (authentication == null) {
        return null; // or throw a custom exception
    }

    String email = null;

    if (authentication instanceof OAuth2AuthenticationToken) {
        var token = (OAuth2AuthenticationToken) authentication;
        var registerId = token.getAuthorizedClientRegistrationId();
        var principal = (DefaultOAuth2User) authentication.getPrincipal();

        if ("google".equalsIgnoreCase(registerId)) {
            email = principal.getAttribute("email");
        } else {
            logger.warn("OAuth2 provider not handled: " + registerId);
        }

    } else {
        email = authentication.getName();
    }

    return email;
}


    public static String getLinkForEmailVerification(String emailToken) {

        String link = "http://localhost:8080/auth/verify-email?token="  + emailToken;

        return link;
    }

}
