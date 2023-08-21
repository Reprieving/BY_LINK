package com.byritium.application;

import com.byritium.application.command.GroupCommand;
import com.byritium.application.command.GroupMemberCommand;
import com.byritium.domain.group.dto.GroupAuth;
import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.domain.group.service.GroupAuthService;
import com.byritium.types.constance.ObjectState;
import com.byritium.types.constance.ResultEnum;
import com.byritium.types.exception.BusinessException;
import com.byritium.utils.AccountHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class GroupAppService {

    @Autowired
    private GroupAuthService groupAuthService;

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

    public void createGroupMember(GroupMemberCommand command) {
        Long accountId = AccountHolder.get();
        GroupMember groupMember = GroupMember.builder()
                .accountId(accountId)
                .groupId(command.getGroupId())
                .identifier(command.getIdentifier())
                .createTime(LocalDateTime.now())
                .os(ObjectState.ENABLE)
                .build();

        GroupAuth groupAuth = new GroupAuth(command.getGroupId(), accountId);
        groupAuthService.authenticate(groupAuth);

        groupRepository.saveGroupMember(groupMember);
    }
}
