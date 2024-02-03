package com.example.cloudnativewebapplication.service.impl;

import com.example.cloudnativewebapplication.model.AppUser;
import com.example.cloudnativewebapplication.repository.UserRepository;
import com.example.cloudnativewebapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean testDatabaseConnection() {
        try {
            userRepository.count(); // A simple operation to test connectivity
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}