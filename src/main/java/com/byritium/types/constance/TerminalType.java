package com.byritium.types.constance;

import lombok.Getter;

@Getter
public enum TerminalType {
    WEB("WEB"),
    APP("APP"),
    DEVICE("DEVICE");

    private final String type;
    TerminalType(String type) {
        this.type = type;
    }

}
