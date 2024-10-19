package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class TooManyLoginAttemptsException extends RuntimeException {
    public TooManyLoginAttemptsException() {
        super(DomainConstants.LOGIN_ATTEMPTS_LIMIT_MESSAGE);
    }
}
