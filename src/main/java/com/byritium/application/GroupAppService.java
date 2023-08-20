package com.byritium.application;

import com.byritium.application.command.GroupCommand;
import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.utils.AccountHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupAppService {

    @Autowired
    private GroupRepository groupRepository;

    public void createGroup(GroupCommand groupCommand) {
        Long accountId = AccountHolder.get();

        Group group = Group.builder()
                .accountId(accountId)
                .groupName(groupCommand.getGroupName())
                .build();
        groupRepository.saveGroup(group);

    }

    public void createGroupMember(GroupCommand groupCommand) {
        GroupMember groupMember = GroupMember.builder()
                .appId(groupCommand.getAppId())
                .groupId(groupCommand.getGroupId())
                .identifier(groupCommand.getGroupName())
                .build();
        groupRepository.saveGroupMember(groupMember);
    }
}
