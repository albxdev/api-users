package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class IdCannotBeNegativeOrZeroException extends RuntimeException {
    public IdCannotBeNegativeOrZeroException() {
        super(DomainConstants.ID_CANNOT_BE_NEGATIVE_OR_ZERO);
    }
}
