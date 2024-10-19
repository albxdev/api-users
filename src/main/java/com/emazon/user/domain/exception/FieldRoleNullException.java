package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldRoleNullException extends RuntimeException {
  public FieldRoleNullException() {
    super(DomainConstants.ROLE_NULL);
  }
}