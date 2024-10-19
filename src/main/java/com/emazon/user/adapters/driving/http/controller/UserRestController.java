package com.emazon.user.adapters.driving.http.controller;

import com.emazon.user.adapters.driving.http.adapter.UserHttpAdapter;
import com.emazon.user.adapters.driving.http.dto.request.CreateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.request.UpdateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.response.CreateUserResponseDTO;
import com.emazon.user.adapters.driving.http.dto.response.UpdateUserResponseDTO;
import com.emazon.user.domain.exception.UserUnauthorizedAccessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserHttpAdapter userHttpAdapter;

    @GetMapping("/api/users/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(
            @Valid @RequestBody CreateUserRequestDTO requestDTO,
            @RequestHeader HttpHeaders headers) {

        if (!isAuthorized(headers)) {
            throw new UserUnauthorizedAccessException();
        }
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

    private boolean isAuthorized(HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
        return authorization != null && authorization.equals("Bearer your-authorization-token");
    }
}