package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldNameNullException extends RuntimeException {
  public FieldNameNullException() {
    super(DomainConstants.FIELD_NAME_NULL_MESSAGE);
  }
}
