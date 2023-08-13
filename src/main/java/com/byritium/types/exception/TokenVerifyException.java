package com.byritium.types.exception;

public class TokenVerifyException extends RuntimeException{
    private String msg;
    public TokenVerifyException(String message) {
        this.msg = message;
    }
}
