package com.byritium.application.command;

import lombok.Data;

@Data
public class AccountCommand {
    private Long accountId;
    private Long appId;
    private String accountName;
    private String accountSecret;
    private String appIdStr;
    private Long uid;
    private String identifier;

}
