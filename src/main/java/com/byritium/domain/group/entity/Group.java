package com.byritium.domain.group.entity;

import com.byritium.types.constance.ObjectState;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Group {
    private long id;
    private Long appId;
    private String groupName;
    private List<GroupMember> members;
    private GroupMember creator;
    private LocalDateTime creteTime;
    private ObjectState os;
}
