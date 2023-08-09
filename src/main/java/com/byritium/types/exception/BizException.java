package com.byritium.types.exception;


public class BizException extends RuntimeException{
    private String msg;
    public BizException(String message) {
        this.msg = message;
    }
}
