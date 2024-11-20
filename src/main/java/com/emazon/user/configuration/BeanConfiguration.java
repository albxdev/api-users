package com.emazon.user.configuration;

import com.emazon.user.adapters.driven.jpa.postgres.adapter.UserAdapter;
import com.emazon.user.adapters.driven.jpa.postgres.mapper.IUserEntityMapper;
import com.emazon.user.adapters.driven.jpa.postgres.repository.IUserRepository;
import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.api.usecase.UserUseCases;
import com.emazon.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper, passwordEncoder());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCases(userPersistencePort());
    }


}