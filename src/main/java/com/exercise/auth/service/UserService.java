package com.exercise.auth.service;

import com.exercise.auth.entity.User;
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

    public User findById(String id) {
        Optional<User> option = userRepository.findByid(id);
        if(!option.isPresent())
            return null;
        return option.get();
    }

    public User registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPwd());
        user.setPwd(encodedPassword);
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> authorities(User user){
        return user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> option = userRepository.findByid(username);
        if(!option.isPresent())
            throw new UsernameNotFoundException("User not found : "+username);
        User user = option.get();
        return new org.springframework.security.core.userdetails.User(user.getId(),user.getPwd(),authorities(user));
    }
}
