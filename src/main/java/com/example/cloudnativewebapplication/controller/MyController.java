package com.example.cloudnativewebapplication.controller;

import com.example.cloudnativewebapplication.model.AppUser;
import com.example.cloudnativewebapplication.repository.UserRepository;
import com.example.cloudnativewebapplication.service.UserService;
import com.example.cloudnativewebapplication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class MyController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/healthz")
    public ResponseEntity<String> checkHealth() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        responseHeaders.set(HttpHeaders.PRAGMA, "no-cache");
        responseHeaders.set(HttpHeaders.EXPIRES, "0");

        boolean dbConnected = userService.testDatabaseConnection();
        if (dbConnected) {
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body("Database connection OK"); //Database connection OK
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .headers(responseHeaders)
                    .body("Database connection failed");
        }
    }

    @GetMapping("/sayhi")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/createusers")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        AppUser savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public String hello(@RequestBody AppUser user) {

        System.out.println("show user: " + user.toString());
        String output = "name: "+user.getName()+ "   |  age: " + user.getAge();

        return output;
    }
}
