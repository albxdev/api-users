package com.emazon.user.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UpdateUserResponseDTO {
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phone;
    private LocalDate birthDate;
    private String email;
}