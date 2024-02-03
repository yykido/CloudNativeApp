package com.example.cloudnativewebapplication.repository;

import com.example.cloudnativewebapplication.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}