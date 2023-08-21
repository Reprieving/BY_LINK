package com.byritium.domain.group.entity;

import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GroupMember {
    private Long accountId;
    private Long groupId;
    private String identifier;
    private LocalDateTime createTime;
    private ObjectState os;
}
