package com.exercise.auth.controller;

import com.exercise.auth.entity.User;
import com.exercise.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping(value="/user")
    public String CreateUser(@RequestBody User userentity){

        return "test";
    }
}
