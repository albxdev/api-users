package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldPhoneLengthException extends RuntimeException {
    public FieldPhoneLengthException() {
        super(DomainConstants.FIELD_PHONE_LENGTH_MESSAGE);
    }
}
