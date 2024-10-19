package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldLastnameNullException extends RuntimeException {
    public FieldLastnameNullException() {
        super(DomainConstants.FIELD_LASTNAME_NULL_MESSAGE);
    }
}
