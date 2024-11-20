package com.emazon.user.adapters.driving.http.dto.request;

import com.emazon.user.domain.utils.DomainConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequestDTO {

    @NotNull(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @NotEmpty(message = DomainConstants.FIELD_NAME_EMPTY_MESSAGE)
    @Size(max = 50, message = DomainConstants.FIELD_NAME_LENGTH_MESSAGE)
    private String firstName;

    @NotNull(message = DomainConstants.FIELD_LASTNAME_NULL_MESSAGE)
    @NotEmpty(message = DomainConstants.FIELD_LASTNAME_EMPTY_MESSAGE)
    @Size(max = 50, message = DomainConstants.FIELD_LASTNAME_LENGTH_MESSAGE)
    private String lastName;

    @NotNull(message = DomainConstants.FIELD_ID_DOCUMENT_NULL_MESSAGE)
    @Pattern(regexp = "\\d+", message = DomainConstants.FIELD_ID_DOCUMENT_INVALID_MESSAGE)
    private String identityDocument;

    @NotNull(message = DomainConstants.FIELD_PHONE_NULL_MESSAGE)
    @Size(max = 13, message = DomainConstants.FIELD_PHONE_LENGTH_MESSAGE)
    private String phone;

    @NotNull(message = DomainConstants.FIELD_BIRTHDATE_NULL_MESSAGE)
    @Past(message = DomainConstants.FIELD_BIRTHDATE_INVALID_MESSAGE)
    private LocalDate birthDate;

    @NotNull(message = DomainConstants.FIELD_EMAIL_NULL_MESSAGE)
    @Email(message = DomainConstants.FIELD_EMAIL_INVALID_MESSAGE)
    private String email;

    @NotNull(message = DomainConstants.FIELD_PASSWORD_NULL_MESSAGE)
    private String password;

    private Long id_role;
}