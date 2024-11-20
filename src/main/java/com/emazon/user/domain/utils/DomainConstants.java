package com.emazon.user.domain.utils;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ID_CANNOT_BE_NEGATIVE_OR_ZERO = "Field 'id' cannot be neither zero nor negative.";

    public static final String INVALID_PAGINATION_PARAMS = "The pagination parameters are invalid.";

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'Name' cannot be null.";
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Field 'Name' cannot be empty.";
    public static final String FIELD_NAME_LENGTH_MESSAGE = "Field 'Name' cannot exceed 50 characters.";

    public static final String FIELD_LASTNAME_NULL_MESSAGE = "Field 'Lastname' cannot be null.";
    public static final String FIELD_LASTNAME_EMPTY_MESSAGE = "Field 'Lastname' cannot be empty.";
    public static final String FIELD_LASTNAME_LENGTH_MESSAGE = "Field 'Lastname' cannot exceed 50 characters.";

    public static final String FIELD_ID_DOCUMENT_NULL_MESSAGE = "Field 'Identity Document' cannot be null.";
    public static final String FIELD_ID_DOCUMENT_INVALID_MESSAGE = "Field 'Identity Document' must be numeric.";

    public static final String FIELD_PHONE_NULL_MESSAGE = "Field 'Phone' cannot be null.";
    public static final String FIELD_PHONE_LENGTH_MESSAGE = "Field 'Phone' must contain a maximum of 13 characters and may include the '+' symbol.";

    public static final String FIELD_BIRTHDATE_NULL_MESSAGE = "Field 'Birthdate' cannot be null.";
    public static final String FIELD_BIRTHDATE_INVALID_MESSAGE = "User must be of legal age.";

    public static final String FIELD_EMAIL_NULL_MESSAGE = "Field 'Email' cannot be null.";
    public static final String FIELD_EMAIL_INVALID_MESSAGE = "Email must have a valid structure.";

    public static final String USERNAME_EMPTY_MESSAGE = "Field 'Username' cannot be empty.";
    public static final String FIELD_PASSWORD_NULL_MESSAGE = "Field 'Password' cannot be null or empty.";
    public static final String FIELD_PASSWORD_ENCRYPTION_MESSAGE = "The password must be stored encrypted.";

    public static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed. Incorrect user or password.";
    public static final String UNAUTHORIZED_ACCESS_MESSAGE = "You do not have permission to access this resource.";

    public static final String LOGIN_ATTEMPTS_LIMIT_MESSAGE = "The number of login attempts is unlimited.";

    public static final String USER_ALREADY_EXISTS = "A user already exists with the provided email.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String FIELD_PHONE_INVALID_MESSAGE_EN = "Field 'Phone' is invalid.";
    public static final String USER_NOT_ADULT_MESSAGE_EN = "User must be of legal age.";

    public static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials provided.";

    public static final String EMAIL_EMPTY = "The email field cannot be empty.";
    public static final String ROLE_NULL = "The role cannot be null.";
    public static final String USER_ROLE_DEFAULT_MESSAGE = "User will have the role of 'aux_bodega'.";
}