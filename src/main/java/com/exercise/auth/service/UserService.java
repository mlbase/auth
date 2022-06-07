package com.exercise.auth.service;

import com.exercise.auth.entity.Users;
import com.exercise.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users findById(String id) {
        Optional<Users> option = userRepository.findByid(id);
        if(!option.isPresent())
            return null;
        return option.get();
    }

    public Users registerUser(Users users){
        String encodedPassword = passwordEncoder.encode(users.getPwd());
        users.setPwd(encodedPassword);
        return userRepository.save(users);
    }

    private Collection<? extends GrantedAuthority> authorities(Users users){
        return users.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> option = userRepository.findByid(username);
        if(!option.isPresent())
            throw new UsernameNotFoundException("User not found : "+username);
        Users users = option.get();
        return new org.springframework.security.core.userdetails.User(users.getId(), users.getPwd(),authorities(users));
    }
}
