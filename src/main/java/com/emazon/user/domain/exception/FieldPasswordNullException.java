package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldPasswordNullException extends RuntimeException {
  public FieldPasswordNullException() {
    super(DomainConstants.FIELD_PASSWORD_NULL_MESSAGE);
  }
}