package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(DomainConstants.USER_ALREADY_EXISTS);
    }
}
