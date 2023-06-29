package com.byritium.domain.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAuth {
    private String username;
    private String password;
    private String identifier;


    public AccountAuth(String username, String identifier) {
        this.username = username;
        this.identifier = identifier;
    }
}
