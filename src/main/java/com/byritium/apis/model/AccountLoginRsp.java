package com.byritium.apis.model;

import lombok.Data;

@Data
public class AccountLoginRsp {
    private String accessToken;

    public AccountLoginRsp(String token) {
        this.accessToken = token;
    }
}
