    package com.emazon.user.configuration;

    public class Constants {

        // Constructor privado para prevenir instanciación
        private Constants() {
            throw new IllegalStateException("Utility class");
        }

        // Mensajes de error para usuarios
        public static final String USER_NOT_FOUND_MESSAGE = "The user with ID '%d' does not exist.";
        public static final String USER_ALREADY_EXISTS_MESSAGE = "The user with ID '%d' already exists.";
        public static final String USER_UNAUTHORIZED_ACCESS_MESSAGE = "Unauthorized access to the user.";

        // Mensajes generales
        public static final String NOT_FOUND_MESSAGE = "The requested entity was not found.";
        public static final String BAD_REQUEST_MESSAGE = "The request was invalid.";
        public static final String FORBIDDEN_MESSAGE = "Access is denied.";
        public static final String GENERAL_ERROR_MESSAGE = "An unexpected error occurred: ";
        public static final String ILLEGAL_ARGUMENT_MESSAGE = "Invalid argument provided: ";
        public static final String VALIDATION_FAILED_MESSAGE = "Validation failed: ";
    }
