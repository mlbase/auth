package com.exercise.auth;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and()   //
                .authorizeRequests()    //
                .antMatchers("/user/sign-up").permitAll()   //sign-up 은 모든 사용자에게 허용
                .antMatchers("/user/user-info").hasAnyAuthority("admin")
                .anyRequest().authenticated().and() //
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
