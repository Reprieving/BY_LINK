package com.byritium.application.command;

import lombok.Data;

@Data
public class AccountCommand {
    private Long appId;
    private Long uid;
    private String identifier;

}
