package com.byritium.types.constance;

import lombok.Getter;

@Getter
public enum TokenVerify {
    SUCCESS("SUCCESS","成功"),
    FAIL("FAIL","失败"),
    EXPIRE("EXPIRE","过期");

    private final String result;
    private final String message;
    TokenVerify(String result, String message) {
        this.result = result;
        this.message = message;
    }

}
