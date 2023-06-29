package com.byritium.domain.account.entity.vo;

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
}
