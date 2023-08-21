package com.byritium.persistence.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.persistence.convertor.GroupConvertor;
import com.byritium.persistence.mapper.GroupMemberPoMapper;
import com.byritium.persistence.mapper.GroupPoMapper;
import com.byritium.persistence.po.GroupMemberPo;
import com.byritium.persistence.po.GroupPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @Autowired
    private GroupPoMapper groupPoMapper;

    @Autowired
    private GroupMemberPoMapper groupMemberPoMapper;

    @Override
    public void saveGroup(Group group) {
        GroupPo groupPo = GroupConvertor.convertorGroupPo(group);
        groupPoMapper.insert(groupPo);
    }

    @Override
    public Group findGroupByAccountId(Long groupId, Long accountId) {
        return GroupConvertor.convertToGroup(groupPoMapper.selectOne(
                new LambdaQueryWrapper<GroupPo>()
                        .eq(GroupPo::getAccountId, accountId)
                        .eq(GroupPo::getId, groupId)));
    }

    @Override
    public void saveGroupMember(GroupMember member) {
        GroupMemberPo po = GroupConvertor.convertorGroupMemberPo(member);
        groupMemberPoMapper.insert(po);
    }

    @Override
    public void removeGroupMember(GroupMember member) {

    }

    @Override
    public List<GroupMember> findMemberByGroup(long groupId) {
        return null;
    }

    @Override
    public List<GroupMember> findMemberByAccountId(long groupId) {
        return null;
    }
}
