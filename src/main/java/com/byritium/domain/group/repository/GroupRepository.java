package com.byritium.domain.group.repository;

import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository {
    void saveGroup(Group group);

    void saveGroupMember(GroupMember member);

    void removeGroupMember(GroupMember member);

    List<GroupMember> findMemberByGroup(long groupId);

    List<GroupMember> findMemberByApp(long groupId);
}
