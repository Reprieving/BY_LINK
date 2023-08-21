package com.byritium.domain.group.repository;

import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;

import java.util.List;

public interface GroupRepository {
    void saveGroup(Group group);

    Group findGroupByAccountId(Long groupId, Long accountId);

    void saveGroupMember(GroupMember member);

    void removeGroupMember(GroupMember member);

    List<GroupMember> findMemberByGroup(long groupId);

    List<GroupMember> findMemberByAccountId(long accountId);
}
