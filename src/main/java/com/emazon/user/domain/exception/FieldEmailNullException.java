package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldEmailNullException extends RuntimeException {
  public FieldEmailNullException() {
    super(DomainConstants.FIELD_EMAIL_NULL_MESSAGE);
  }
}