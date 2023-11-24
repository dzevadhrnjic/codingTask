package com.zira.codingtask.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {

    public InvalidEmailOrPasswordException(String exception) {
        super(exception);
    }
}
