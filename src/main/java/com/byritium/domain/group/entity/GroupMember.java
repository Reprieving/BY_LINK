package com.byritium.domain.group.entity;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GroupMember {
    private Long appId;
    private Long groupId;
    private String identifier;
}
