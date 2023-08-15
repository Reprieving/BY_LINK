package com.byritium.application.command;

import lombok.Data;

@Data
public class GroupCommand {
    private Long appId;
    private String groupName;
    private String identifier;
    private Long groupId;
}
