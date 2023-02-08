package com.byritium.conn.infra.general.constance;

import lombok.Getter;

@Getter
public enum CustomerType {
    HTTP("HTTP"),
    WEBSOCKET("WEBSOCKET"),
    TCP("TCP"),
    UDP("UDP"),
    MQTT("MQTT"),
    COAP("COAP");

    private final String type;
    CustomerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
