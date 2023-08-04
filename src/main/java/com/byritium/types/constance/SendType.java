package com.byritium.types.constance;

import lombok.Getter;

@Getter
public enum SendType {
    POINT_SEND("POINT","点发送"),
    GROUP_SEND("GROUP","组发送"),
    APPLICATION_SEND("APPLICATION_SEND","应用发送");

    private final String type;
    private final String message;
    SendType(String type, String message) {
        this.type = type;
        this.message = message;
    }

}
