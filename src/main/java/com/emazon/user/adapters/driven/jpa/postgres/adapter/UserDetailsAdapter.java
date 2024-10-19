package com.emazon.user.adapters.driven.jpa.postgres.adapter;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.postgres.repository.IUserRepository;
import com.emazon.user.adapters.driven.jpa.postgres.mapper.IUserEntityMapper;
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

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity userEntity = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            com.emazon.user.domain.model.User user = userEntityMapper.toDomain(userEntity);

            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        };
    }
}