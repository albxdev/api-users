package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldLastnameLengthException extends RuntimeException {
    public FieldLastnameLengthException() {
        super(DomainConstants.FIELD_LASTNAME_LENGTH_MESSAGE);
    }
}
