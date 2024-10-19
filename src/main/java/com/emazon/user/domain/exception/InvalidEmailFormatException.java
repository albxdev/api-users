package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super(DomainConstants.FIELD_EMAIL_INVALID_MESSAGE);
    }
}