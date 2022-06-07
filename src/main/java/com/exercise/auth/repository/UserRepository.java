package com.exercise.auth.repository;

import com.exercise.auth.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {


    
}
