package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldNameEmptyException extends RuntimeException {
    public FieldNameEmptyException() {
        super(DomainConstants.FIELD_NAME_EMPTY_MESSAGE);
    }
}
