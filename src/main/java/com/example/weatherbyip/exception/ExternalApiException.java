package com.example.weatherbyip.exception;

public class ExternalApiException extends RuntimeException {
public ExternalApiException(String message) {
	super(message);
}

public ExternalApiException(String message, Throwable cause) {
    super(message, cause);
}
}
