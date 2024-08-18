package com.devil.ecomfashion.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String message;

    ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
