package com.emazon.user.adapters.driven.jpa.postgres.exception;

public class UserNoDataFoundException extends RuntimeException {
    public UserNoDataFoundException() {
        super("No users found in the database.");
    }
}