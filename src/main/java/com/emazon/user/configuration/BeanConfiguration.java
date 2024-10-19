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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
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