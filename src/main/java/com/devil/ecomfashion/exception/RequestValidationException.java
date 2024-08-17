package com.devil.ecomfashion.exception;

public class RequestValidationException extends RuntimeException {

    public RequestValidationException(Throwable cause) {
        super(cause);
    }

    public RequestValidationException() {
    }

    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}