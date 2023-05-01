package com.jrosas.test.springboot.app.springboottest.exceptions;

public class InsuficientMoneyException extends RuntimeException {
    public InsuficientMoneyException(String message) {
        super(message);
    }
}
