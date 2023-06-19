package com.byritium.constance;

import lombok.Getter;

@Getter
public enum MessagePayloadType {
    IOT("IOT","IOT"),
    USER("USER","用户");

    private final String type;
    private final String message;
    MessagePayloadType(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
