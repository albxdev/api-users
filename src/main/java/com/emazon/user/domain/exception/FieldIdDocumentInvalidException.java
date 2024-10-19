package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldIdDocumentInvalidException extends RuntimeException {
  public FieldIdDocumentInvalidException() {
    super(DomainConstants.FIELD_ID_DOCUMENT_INVALID_MESSAGE);
  }
}