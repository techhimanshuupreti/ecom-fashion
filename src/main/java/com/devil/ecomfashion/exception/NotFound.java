package com.devil.ecomfashion.exception;

import org.springframework.stereotype.Service;

@Service
public class NotFound extends RuntimeException {

    private String message;

    NotFound() {
        super();
    }

    public NotFound(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
