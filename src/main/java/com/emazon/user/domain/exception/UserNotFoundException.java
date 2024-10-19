package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(DomainConstants.USER_NOT_FOUND);
    }
}
