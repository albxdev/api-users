package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class UserUnauthorizedAccessException extends RuntimeException {
  public UserUnauthorizedAccessException() {
    super(DomainConstants.UNAUTHORIZED_ACCESS_MESSAGE);
  }
}