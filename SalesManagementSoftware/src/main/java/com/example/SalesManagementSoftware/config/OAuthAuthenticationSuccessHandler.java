package com.example.SalesManagementSoftware.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.SalesManagementSoftware.repository.PageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import com.example.SalesManagementSoftware.entity.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private PageRepository repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");

        // Get user details from OAuth2 - using OAuth2User interface instead
        DefaultOAuth2User user  = (DefaultOAuth2User)authentication.getPrincipal();
        
        // logger.info(user.getName());
        
        // user.getAttributes().forEach((key, value) -> {
        //     logger.info("{} : {}", key, value);
        // });

        // logger.info(user.getAuthorities().toString());

        //identify the provider
        var oauth = (OAuth2AuthenticationToken)authentication;
        String clientId = oauth.getAuthorizedClientRegistrationId();
        
        logger.info(clientId);

        Employee user1 = new Employee();
        user1.setRole(Role.ADMIN);
        user1.setProviderURL(user.getName());
        user1.setEnabled(true);
        user1.setEmailVerified(true);



        if ("google".equals(clientId)) {

            String email = user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();

            user1.setName(name);
            user1.setEmail(email);
            user1.setProfileLink(picture);
            user1.setPassword("password");
            user1.setProvider(Providers.GOOGLE);
            user1.setProviderURL(user.getName());
            user1.setAbout("This account was created using google");

        } 
        
        //fetching data and saving to database
        Employee user3 = repo.findByEmail(user1.getEmail()).orElse(null);

        if (user3 == null) {
            repo.save(user1);
            logger.info("User saved .... " + user1.getEmail());
        } else {
            logger.info("User already present" + user1.getEmail());
        }

        response.sendRedirect("/user/profile");
    }
    
}
