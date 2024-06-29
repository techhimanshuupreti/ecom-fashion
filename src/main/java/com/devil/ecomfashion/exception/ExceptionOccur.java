package com.devil.ecomfashion.exception;

public class ExceptionOccur extends RuntimeException {
    public ExceptionOccur() {
    }

    public ExceptionOccur(String message) {
        super(message);
    }

    public ExceptionOccur(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionOccur(Throwable cause) {
        super(cause);
    }

    public ExceptionOccur(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
