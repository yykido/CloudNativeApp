package com.example.cloudnativewebapplication.service;

import com.example.cloudnativewebapplication.model.AppUser;
import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser saveUser(AppUser user);
    Optional<AppUser> getUserById(Long id);
    List<AppUser> getAllUsers();
    void deleteUser(Long id);
    boolean testDatabaseConnection();
}