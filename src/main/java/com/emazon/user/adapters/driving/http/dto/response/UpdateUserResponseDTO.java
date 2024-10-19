package com.emazon.user.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateUserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
}