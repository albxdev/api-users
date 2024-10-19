package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldBirthdateInvalidException extends RuntimeException {
  public FieldBirthdateInvalidException() {
    super(DomainConstants.FIELD_BIRTHDATE_INVALID_MESSAGE);
  }
}