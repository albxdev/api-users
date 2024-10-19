package com.emazon.user.domain.exception;

import com.emazon.user.domain.utils.DomainConstants;

public class InvalidPaginationParamsException extends RuntimeException {
    public InvalidPaginationParamsException() {
        super(DomainConstants.INVALID_PAGINATION_PARAMS);
    }
}
