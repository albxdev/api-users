package com.emazon.user.adapters.driven.jpa.postgres.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class RoleCannotBeNullException extends RuntimeException {
  public RoleCannotBeNullException() {
    super(DomainConstants.ROLE_NULL);
  }
}