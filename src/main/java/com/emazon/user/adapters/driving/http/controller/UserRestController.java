package com.emazon.user.adapters.driving.http.controller;

import com.emazon.user.adapters.driving.http.adapter.UserHttpAdapter;
import com.emazon.user.adapters.driving.http.dto.request.CreateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.request.LoginRequest;
import com.emazon.user.adapters.driving.http.dto.request.UpdateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.response.CreateUserResponseDTO;
import com.emazon.user.adapters.driving.http.dto.response.UpdateUserResponseDTO;
import com.emazon.user.domain.exception.FieldBirthdateInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final PasswordEncoder passwordEncoder;
    private final UserHttpAdapter userHttpAdapter;
    private final UserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        log.info("Received login request: {}", loginRequest);

        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            log.warn("Login request is missing username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password must be provided");
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Use the injected userDetailsService to load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Verify password using BCrypt
        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            log.info("Login successful for user: {}", username);
            String token = Jwts.builder()
                    .setSubject(username)
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
            return ResponseEntity.ok(token);
        } else {
            log.warn("Login failed for user: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(
            @Valid @RequestBody CreateUserRequestDTO requestDTO,
            @RequestHeader(HttpHeaders.AUTHORIZATION) HttpHeaders headers) {

        if (!isAuthorized(headers)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Validate birthdate and create user logic...
        if (requestDTO.getBirthDate() != null) {
            int age = Period.between(requestDTO.getBirthDate(), LocalDate.now()).getYears();
            if (age < 18) {
                throw new FieldBirthdateInvalidException();
            }
        }

        String encryptedPassword = passwordEncoder.encode(requestDTO.getPassword());
        requestDTO.setPassword(encryptedPassword);

        // Check role ID
        if (requestDTO.getId_role() == null) {
            throw new IllegalArgumentException("Se debe proporcionar un id de rol.");
        }

        // Add logic to create the user, potentially checking the role ID against the database
        log.info("Recibiendo solicitud para crear usuario: {}", requestDTO);
        CreateUserResponseDTO responseDTO = userHttpAdapter.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDTO> getUserById(@PathVariable Long id) {
        UpdateUserResponseDTO response = userHttpAdapter.getUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CreateUserResponseDTO>> listUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        List<CreateUserResponseDTO> response = userHttpAdapter.listUsers(page, size);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDTO> updateUserById(@PathVariable Long id, @RequestBody @Valid UpdateUserRequestDTO request) {
        UpdateUserResponseDTO response = userHttpAdapter.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userHttpAdapter.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Método para verificar la autorización
    private boolean isAuthorized(HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("roles");

            return roles != null && roles.contains("ROLE_ADMIN");
        }
        return false;
    }
}