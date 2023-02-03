package com.thoughtworks.main;

public class InvalidOverdraftLimitException extends RuntimeException {
    public InvalidOverdraftLimitException(String message) {
        super(message);
    }
}
