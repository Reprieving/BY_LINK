package com.byritium.types.exception;


public class AccountAuthException extends RuntimeException{
    private String msg;
    public AccountAuthException(String message) {
        this.msg = message;
    }
}
