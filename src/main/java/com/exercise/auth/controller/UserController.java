package com.exercise.auth.controller;
//https://junhyunny.github.io/spring-boot/spring-security/spring-security-example/
import com.exercise.auth.entity.Users;
import com.exercise.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void CreateUser(@RequestParam Users userentity){
        service.registerUser(userentity);

    }

    @PostMapping(value="/login")
    public Users loginuser(@RequestParam Users users){
        String pwd = users.getPwd();
        String encodedPwd = service.findById(users.getId()).getPwd();
        boolean b = encoder.matches(pwd,encodedPwd);
        if(b)
            return service.findById(users.getId());
        else
            return null;

    }


    @GetMapping(value = "/user-info")
    public Users getUser(@RequestParam Users users){
        return service.findById(users.getId());
    }
}
