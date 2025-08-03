package com.example.SalesManagementSoftware.services;

import java.util.List;
import java.util.Optional;

import com.example.SalesManagementSoftware.entity.Employee;

public interface UserService {
    
    Employee saveUser(Employee user);
    
    Optional<Employee> getUserById(String id);
    
    Optional<Employee> updateUser(Employee user);
    
    void deleteUser(String id);
    
    boolean isUserExists(String userId);
    
    boolean isUserExistsByEmail(String email);
    
    List<Employee> getAllUsers();

    Employee getUserByEmail(String email);

    Employee getUserByEmailToken(String token);

}
