package com.byritium.infra.general.constance;

import lombok.Getter;

@Getter
public enum CustomerType {
    USER("USER","用户"),
    DEVICE("DEVICE","设备");

    private final String type;
    private final String message;
    CustomerType(String type, String message) {
        this.type = type;
        this.message = message;
    }

}
