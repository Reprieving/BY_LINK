package com.byritium.domain.group.service;

import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.domain.group.dto.GroupAuth;
import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.types.constance.ResultEnum;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.types.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupAuthServiceImpl implements GroupAuthService {
    @Autowired
    private GroupRepository groupRepository;

    public Group authenticate(GroupAuth groupAuth) {
        Group group = groupRepository.findGroupByAccountId(groupAuth.getGroupId(), groupAuth.getAccountId());
        if (group == null) {
            throw new BusinessException(ResultEnum.ACCOUNT_EXIST);
        }
        return group;
    }
}
