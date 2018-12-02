package com.dangde.exception;


public class AException extends RuntimeException {
    public AException(String message) {
        super(message);
    }

    public AException(String message, Throwable cause) {
        super(message, cause);
    }
}
