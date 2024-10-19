package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldPhoneInvalidException extends RuntimeException {
    public FieldPhoneInvalidException() {
        super(DomainConstants.FIELD_PHONE_INVALID_MESSAGE_EN);
    }
}
