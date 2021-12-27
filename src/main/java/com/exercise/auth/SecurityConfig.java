package com.exercise.auth;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/info").hasRole("Member")
                    .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("nickname")
                    .passwordParameter("pwd")
                    .defaultSuccessUrl("/login/result")
                    .permitAll()
                .and()
                    .csrf()
                    .ignoringAntMatchers("/main")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher(("/logout")))
                    .logoutSuccessUrl("/index")
                    .invalidateHttpSession(true)
                    .deleteCookies("jwtToken")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
