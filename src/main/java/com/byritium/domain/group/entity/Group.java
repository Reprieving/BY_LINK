package com.byritium.domain.group.entity;

import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Group {
    private Long id;
    private Long accountId;
    private String groupName;
    private List<GroupMember> members;
    private String createIdentifier;
    private GroupMember creator;
    private LocalDateTime createTime;
    private ObjectState os;
}
