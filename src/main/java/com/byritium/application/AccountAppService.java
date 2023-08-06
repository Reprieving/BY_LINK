package com.byritium.application;

import com.byritium.application.command.AccountCommand;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountAuth;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountAppService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void resisterAccount(AccountCommand accountCommand){
        Account account = new Account();
        accountRepository.saveAccount(account);
    }

    public void joinGroup(){
        GroupMember groupMember = new GroupMember();
        groupRepository.saveGroupMember(groupMember);
    }

    public void quitGroup(){
        GroupMember groupMember = new GroupMember();
        groupRepository.removeGroupMember(groupMember);
    }
}
