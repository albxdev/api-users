package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super(DomainConstants.INVALID_CREDENTIALS_MESSAGE);
    }
}