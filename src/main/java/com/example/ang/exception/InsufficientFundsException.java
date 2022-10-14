package com.example.ang.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String s) {
        super(s);
    }
}
