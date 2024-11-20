package com.emazon.user.adapters.driven.jpa.postgres.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class UserDetailsAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("admin".equals(username)) {
                return User.withUsername("admin")
                        .password("{bcrypt}" + "$2a$10$exampleHashForAdminPassword") // Hashed password here
                        .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .build();
            } else if ("aux_bodega".equals(username)) {
                return User.withUsername("aux_bodega")
                        .password("{bcrypt}" + "$2a$10$exampleHashForAuxPassword") // Hashed password here
                        .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                        .build();
            }
            throw new UsernameNotFoundException("User not found with username: " + username);
        };
    }
}