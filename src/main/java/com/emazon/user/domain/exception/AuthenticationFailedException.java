package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super(DomainConstants.AUTHENTICATION_FAILED_MESSAGE);
    }
}