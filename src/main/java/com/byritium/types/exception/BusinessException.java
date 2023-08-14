package com.byritium.types.exception;


import com.byritium.types.constance.ResultEnum;

public class BusinessException extends RuntimeException{
    private final ResultEnum expType;
    private final String msg;
    public BusinessException(ResultEnum expType) {
        this.expType = expType;
        this.msg = expType.getMessage();
    }

    public ResultEnum getExpType() {
        return expType;
    }

    public String getMsg() {
        return msg;
    }
}
