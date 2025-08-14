package com.example.SalesManagementSoftware.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.entity.Providers;
import com.example.SalesManagementSoftware.entity.Role;
import com.example.SalesManagementSoftware.repository.PageRepository;
import com.example.SalesManagementSoftware.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserService service;

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
        user1.setProviderURL(user.getName());

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
            user1.setEnabled(false);
            user1.setEmailVerified(false);
            user1.setRole(Role.EMPLOYEE);
            service.saveUser(user1, true);
            logger.info("User saved .... " + user1.getEmail());
        } else {
            user1.setEnabled(true);
            user1.setEmailVerified(true);
            user1.setRole(user3.getRole());
            logger.info("User already present" + user1.getEmail());
        }

        if (!user1.isEnabled()) {
            // Prevent login
            response.sendRedirect("/login?error=verification");
            return;
        }

        
        response.sendRedirect("/user/profile");
    }
    
}
