package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class UserNotAdultException extends RuntimeException {
    public UserNotAdultException() {
        super(DomainConstants.USER_NOT_ADULT_MESSAGE_EN);
    }
}
