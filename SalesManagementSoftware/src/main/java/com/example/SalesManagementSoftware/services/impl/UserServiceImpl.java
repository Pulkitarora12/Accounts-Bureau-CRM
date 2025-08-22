package com.example.SalesManagementSoftware.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.SalesManagementSoftware.Helper.Helper;
import com.example.SalesManagementSoftware.entity.Employee;
import com.example.SalesManagementSoftware.entity.Role;
import com.example.SalesManagementSoftware.repository.PageRepository;
import com.example.SalesManagementSoftware.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private PageRepository repo;

    @Autowired
    private EmailService emailService;

    public UserServiceImpl(PageRepository repo) {
        this.repo = repo;
    }

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public Employee saveUser(Employee user, boolean isNewUser) {
        if (isNewUser) {
            user.setRole(Role.EMPLOYEE);
            String emailToken = UUID.randomUUID().toString();
            String emailLink = Helper.getLinkForEmailVerification(emailToken);
            user.setEmailToken(emailToken);
            emailService.sendEmail(
                email,
                "Verify Account : Email Contact Manager", 
                emailLink
            );
        }
        System.out.println("Authorities: " + user.getAuthorities());
        return repo.save(user);
    }


    @Override
    public Optional<Employee> getUserById(Long id) {
        
        return repo.findById(id);
    }

    @Override
    public Optional<Employee> updateUser(Employee user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        Employee employee = repo.findById(id).orElse(null);

        if (employee != null) {
            repo.delete(employee);
        }
    }

    @Override
    public boolean isUserExists(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExists'");
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExistsByEmail'");
    }

    @Override
    public List<Employee> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public Employee getUserByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public Employee getUserByEmailToken(String token) {
        
        return repo.findByEmailToken(token).orElse(null);
    }


    @Override
    public Page<Employee> getAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                                                  : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

}
