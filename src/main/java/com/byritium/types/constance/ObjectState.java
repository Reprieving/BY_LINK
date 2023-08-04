package com.byritium.types.constance;

import lombok.Getter;

@Getter
public enum ObjectState {
    NORMAL("WEB"),
    DELETE("APP"),
    DEVICE("DEVICE");

    private final String type;
    ObjectState(String type) {
        this.type = type;
    }

}

