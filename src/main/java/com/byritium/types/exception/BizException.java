package com.byritium.types.exception;


import com.byritium.types.constance.ResultEnum;

public class BizException extends RuntimeException{
    private ResultEnum expType;
    private String msg;
    public BizException(ResultEnum expType) {
        this.expType = expType;
        this.msg = expType.getMessage();
    }
}
