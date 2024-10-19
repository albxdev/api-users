package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class InvalidIdentityDocumentException extends RuntimeException {
    public InvalidIdentityDocumentException() {
        super(DomainConstants.FIELD_ID_DOCUMENT_INVALID_MESSAGE);
    }
}