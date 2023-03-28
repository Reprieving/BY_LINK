package com.byritium.conn.infra.general.constance;

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
