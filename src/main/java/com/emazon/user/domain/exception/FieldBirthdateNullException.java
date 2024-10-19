package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldBirthdateNullException extends RuntimeException {
    public FieldBirthdateNullException() {
        super(DomainConstants.FIELD_BIRTHDATE_NULL_MESSAGE);
    }
}
