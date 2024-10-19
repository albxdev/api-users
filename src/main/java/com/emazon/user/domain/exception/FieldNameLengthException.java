package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldNameLengthException extends RuntimeException {
  public FieldNameLengthException() {
    super(DomainConstants.FIELD_NAME_LENGTH_MESSAGE);
  }
}
