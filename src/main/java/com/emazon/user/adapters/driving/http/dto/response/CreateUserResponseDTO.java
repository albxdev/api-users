package com.emazon.user.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String document;
    private String phone;
    private String birthDate;
    private String email;
}