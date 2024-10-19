package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldEmailInvalidException extends RuntimeException {
    public FieldEmailInvalidException() {
        super(String.format(DomainConstants.FIELD_EMAIL_INVALID_MESSAGE));
    }
}
