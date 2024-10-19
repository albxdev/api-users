package com.emazon.user.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JWTTokenProvider tokenProvider;

    @Value("${security.jwt.uri}")
    private String jwtUri;

    @Value("${security.jwt.header}")
    private String jwtHeader;

    @Value("${security.jwt.prefix}")
    private String jwtPrefix;

    public SecurityConfig(JWTTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // Permitir acceso a estas rutas sin autenticación
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/login",
                                "/api/users/register",
                                jwtUri
                        ).permitAll()
                        // Configurar permisos para rutas específicas antes de anyRequest
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Solo admin puede acceder a /admin/**
                        .requestMatchers("/user/**").hasRole("USER") // Solo user puede acceder a /user/**
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                );

        // Agregar filtros JWT
        http.addFilter(new JWTAuthenticationFilter(authManager(http), tokenProvider, jwtHeader, jwtPrefix));
        http.addFilterBefore(new JWTAuthorizationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }
}