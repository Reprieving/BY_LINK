package com.byritium.conn.infra.general.constance;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ProtocolType {
    USER("USER","用户"),
    DEVICE("DEVICE","设备");

    private final String type;
    private final String message;
    ProtocolType(String type,String message) {
        this.type = type;
        this.message = message;
    }

}
