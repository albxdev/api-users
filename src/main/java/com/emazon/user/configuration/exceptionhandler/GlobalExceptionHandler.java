package com.emazon.user.configuration.exceptionhandler;

import com.emazon.user.adapters.driven.jpa.postgres.exception.RoleCannotBeNullException;
import com.emazon.user.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Manejo de validaciones de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : errors) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.value()) // Asegúrate de que se incluya este campo
                .error("Validation failed")
                .message("Validation failed")
                .path(request.getDescription(false).substring(4))
                .fieldErrors(fieldErrors)
                .build();

        logger.error("Validation error: {}", fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldBirthdateInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleFieldBirthdateInvalidException(FieldBirthdateInvalidException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.value()) // Asegúrate de que se incluya este campo
                .error("Bad Request") // Descripción del error
                .message(ex.getMessage())
                .path(request.getDescription(false).substring(4))
                .build();

        logger.error("Field birthdate invalid: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleCustomExceptions(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Valor por defecto
        String errorMessage = ex.getMessage();

        // Manejo de Unauthorized
        if (ex instanceof UserUnauthorizedAccessException) {
            status = HttpStatus.UNAUTHORIZED;
        }
        // Manejo de NotFound
        else if (ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }
        // Manejo de BadRequest
        else if (ex instanceof InvalidCredentialsException) {
            status = HttpStatus.BAD_REQUEST;
            errorMessage = "Invalid user credentials provided.";
        }

        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .statusCode(status.value()) // Asegúrate de que se incluya este campo
                .error("Error")
                .message(errorMessage)
                .path(request.getDescription(false).substring(4))
                .build();

        logger.error("Custom exception caught: {}", errorMessage);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(RoleCannotBeNullException.class)
    public ResponseEntity<ExceptionResponse> handleRoleCannotBeNull(RoleCannotBeNullException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.value()) // Asegúrate de que se incluya este campo
                .error("Bad Request") // Descripción del error
                .message(ex.getMessage())
                .path(request.getDescription(false).substring(4))
                .build();

        logger.error("Role cannot be null: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()) // Asegúrate de que se incluya este campo
                .error("Internal Server Error") // Descripción del error
                .message(ex.getMessage())
                .path(request.getDescription(false).substring(4))
                .build();

        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}