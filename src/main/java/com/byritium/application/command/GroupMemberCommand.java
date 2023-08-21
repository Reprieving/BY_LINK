package com.byritium.application.command;

import lombok.Data;

@Data
public class GroupMemberCommand {
    private Long groupId;
    private String identifier;
}
