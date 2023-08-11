package com.byritium.application.command;

import lombok.Data;

@Data
public class AccountCommand {
    private Long accountId;
    private Long appId;
    private String appIdStr;
    private Long uid;
    private String identifier;

}
