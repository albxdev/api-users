package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldPhoneNullException extends RuntimeException {
  public FieldPhoneNullException() {
    super(DomainConstants.FIELD_PHONE_NULL_MESSAGE);
  }
}
