package com.emazon.user.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;
    private final String jwtHeader;
    private final String jwtPrefix;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTTokenProvider tokenProvider,
                                   String jwtHeader, String jwtPrefix, String jwtUri) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.jwtHeader = jwtHeader;
        this.jwtPrefix = jwtPrefix;
        setFilterProcessesUrl(jwtUri);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String usernameOrEmail = request.getParameter("username") != null ? request.getParameter("username") : request.getParameter("admin");
        String password = request.getParameter("password");

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        String token = tokenProvider.generateToken(authResult.getName(), authorities);
        response.addHeader(jwtHeader, jwtPrefix + " " + token);
        chain.doFilter(request, response);
    }
}