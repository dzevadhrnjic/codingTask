package com.zira.codingtask.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String runtimeException) {
        super(runtimeException);
    }
}
