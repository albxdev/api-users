package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class InvalidPhoneFormatException extends RuntimeException {
    public InvalidPhoneFormatException() {
        super(DomainConstants.FIELD_PHONE_INVALID_MESSAGE_EN);
    }
}