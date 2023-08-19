package com.byritium.domain.group.entity;

import com.byritium.types.CommonAttr;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GroupMember extends CommonAttr {
    private Long appId;
    private Long groupId;
    private String identifier;
}
