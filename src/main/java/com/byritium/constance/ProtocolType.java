package com.byritium.constance;

import lombok.Getter;

@Getter
public enum ProtocolType {
    HTTP("HTTP"),
    WEBSOCKET("WEBSOCKET"),
    TCP("TCP"),
    UDP("UDP"),
    MQTT("MQTT"),
    COAP("COAP");

    private final String type;
    ProtocolType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
