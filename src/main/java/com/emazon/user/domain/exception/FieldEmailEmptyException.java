package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldEmailEmptyException extends RuntimeException {
    public FieldEmailEmptyException() {
        super(DomainConstants.EMAIL_EMPTY);
    }
}