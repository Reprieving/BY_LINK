package com.byritium.persistence.repository;

import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @Override
    public void saveGroup(Group group) {

    }

    @Override
    public void saveGroupMember(GroupMember member) {

    }

    @Override
    public void removeGroupMember(GroupMember member) {

    }

    @Override
    public List<GroupMember> findMemberByGroup(long groupId) {
        return null;
    }

    @Override
    public List<GroupMember> findMemberByApp(long groupId) {
        return null;
    }
}
