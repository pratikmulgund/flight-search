package com.demo.flightsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The type Security config. For this test application, creates two users and authenticates against their roles
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Create 2 users just for this demo project
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("USER")
                .and()
                .withUser("manager")
                .password("{noop}password")
                .roles("USER", "ADMIN");

    }

    // Secure endpoints with HTTP Basic authentication.
    // In actual production ready application this will mostly be token based authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/airports/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/airports").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/airports/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/airports/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/airlines/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/airlines").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/airlines/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/airlines/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/routes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/routes").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/routes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/routes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/search-flights?**").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
