package com.byritium.persistence.po;

import lombok.Data;

@Data
public class AccountIdentifierPo {
    private Long id;
    private Long accountId;
    private String username;
    private String password;
    private String identify;
}
