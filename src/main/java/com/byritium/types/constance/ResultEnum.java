package com.byritium.types.constance;

import lombok.Getter;

/**
 * @author djh
 * @version 1.0
 * @date 2020/11/12/012 11:54
 * @description 返回编码的标签
 **/

@Getter
public enum ResultEnum {

    SUCCESS(0, "SUCCESS"),
    ERROR_CODE(-1, "失败"),
    EMPTY(1, "EMPTY DATA"),

    ACCOUNT_EXIST(1000, "account exist"),
    ACCOUNT_IDENTIFIER_EXIST(1000, "account identifier exist"),
    ACCOUNT_VERIFY_FAIL(1000, "account verify fail"),
    ACCOUNT_WRONG_SECRET(1000, "wrong password"),
    ;


    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
