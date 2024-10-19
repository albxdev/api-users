package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldLastnameEmptyException extends RuntimeException {
  public FieldLastnameEmptyException() {
    super(DomainConstants.FIELD_LASTNAME_EMPTY_MESSAGE);
  }
}
