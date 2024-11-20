package com.emazon.user.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private BigInteger id_role;
}