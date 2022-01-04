package com.exercise.auth.controller;
//https://junhyunny.github.io/spring-boot/spring-security/spring-security-example/
import com.exercise.auth.entity.User;
import com.exercise.auth.exception.UserNotFoundException;
import com.exercise.auth.repository.UserRepository;
import com.exercise.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping(value="/user/sign-up")
    public void CreateUser(@RequestParam User userentity){
        service.registerUser(userentity);

    }

    @GetMapping(value = "/user/user-info")
    public User getUser(@RequestParam User user){
        return service.findById(user.getId());
    }
}
