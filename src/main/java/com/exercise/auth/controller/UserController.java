package com.exercise.auth.controller;
//https://junhyunny.github.io/spring-boot/spring-security/spring-security-example/
import com.exercise.auth.entity.User;
import com.exercise.auth.exception.UserNotFoundException;
import com.exercise.auth.repository.UserRepository;
import com.exercise.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping(value="/sign-up")
    @Transactional(propagation = Propagation.REQUIRED)
    public void CreateUser(@RequestParam User userentity){
        service.registerUser(userentity);

    }

    @PostMapping(value="/login")
    public User loginuser(@RequestParam User user){
        String pwd = user.getPwd();
        String encodedPwd = service.findById(user.getId()).getPwd();
        boolean b = encoder.matches(pwd,encodedPwd);
        if(b)
            return service.findById(user.getId());
        else
            return null;

    }


    @GetMapping(value = "/user-info")
    public User getUser(@RequestParam User user){
        return service.findById(user.getId());
    }
}
