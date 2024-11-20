package com.emazon.user.configuration.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private int status;
    private int statusCode;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;
}