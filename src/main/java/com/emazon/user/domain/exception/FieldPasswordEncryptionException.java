package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class FieldPasswordEncryptionException extends RuntimeException {
  public FieldPasswordEncryptionException() {
    super(DomainConstants.FIELD_PASSWORD_ENCRYPTION_MESSAGE);
  }
}