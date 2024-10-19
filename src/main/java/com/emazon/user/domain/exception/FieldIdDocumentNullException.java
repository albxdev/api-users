package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldIdDocumentNullException extends RuntimeException {
    public FieldIdDocumentNullException() {
        super(DomainConstants.FIELD_ID_DOCUMENT_NULL_MESSAGE);
    }
}
