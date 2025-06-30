package com.example.weatherbyip.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {

	 @ExceptionHandler(ExternalApiException.class)
	    public ResponseEntity<Object> handleExternalApiException(ExternalApiException ex) {
	        log.error("External API error: {}", ex.getMessage());
	        return buildErrorResponse(HttpStatus.BAD_GATEWAY, "External service failure", ex.getMessage());
	    }

	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Object> handleValidationErrors(ConstraintViolationException ex) {
	        log.warn("Validation error: {}", ex.getMessage());
	        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input", ex.getMessage());
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleMethodArgumentErrors(MethodArgumentNotValidException ex) {
	        log.warn("Method argument error: {}", ex.getMessage());
	        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", ex.getMessage());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleAllOtherExceptions(Exception ex) {
	        log.error("Unhandled exception: ", ex);
	        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", "Something went wrong.");
	    }

	    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", status.value());
	        response.put("error", error);
	        response.put("message", message);
	        return new ResponseEntity<>(response, status);
	    }
}
