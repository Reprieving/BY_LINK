package com.byritium.types.constance;

import lombok.Getter;

@Getter
public enum ObjectState {
    ENABLE("ENABLE"),
    DELETE("DELETE");

    private final String type;
    ObjectState(String type) {
        this.type = type;
    }

}

