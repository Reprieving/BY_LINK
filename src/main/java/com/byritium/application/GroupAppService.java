package com.byritium.application;

import com.byritium.application.command.GroupCommand;
import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupAppService {

    @Autowired
    private GroupRepository groupRepository;

    public void createGroup(GroupCommand groupCommand) {

        Group group = Group.builder()
                .groupName(groupCommand.getGroupName())
                .build();
        groupRepository.saveGroup(group);

    }

    public void saveGroupMember() {

    }
}
