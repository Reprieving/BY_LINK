package com.byritium.types.exception;


import com.byritium.types.constance.ResultEnum;

public class BusinessException extends RuntimeException{
    private final ResultEnum expType;
    private final String message;
    public BusinessException(ResultEnum expType) {
        this.expType = expType;
        this.message = expType.getMessage();
    }

    public ResultEnum getExpType() {
        return expType;
    }

    public String getMessage() {
        return message;
    }
}
