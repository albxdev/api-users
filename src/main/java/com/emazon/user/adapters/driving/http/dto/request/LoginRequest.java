package com.emazon.user.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import com.emazon.user.domain.utils.DomainConstants;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = DomainConstants.USERNAME_EMPTY_MESSAGE)
    private String username;

    @NotEmpty(message = DomainConstants.FIELD_PASSWORD_NULL_MESSAGE)
    private String password;
}