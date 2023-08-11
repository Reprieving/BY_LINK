package com.byritium.application;

import com.byritium.application.command.AccountCommand;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.types.constance.ObjectState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AccountAppService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void resisterAccount(AccountCommand accountCommand) {
        Account account = Account.builder()
                .appId(accountCommand.getAppId())
                .createTime(LocalDateTime.now())
                .os(ObjectState.ENABLE)
                .build();

        accountRepository.saveAccount(account);
    }

    public void createIdentifier(AccountCommand accountCommand) {
        Account account = accountRepository.findAccountById(accountCommand.getAccountId());

        AccountIdentifier accountIdentifier = AccountIdentifier.builder()
                .accountId(account.getId())
                .identifier(UUID.randomUUID().toString())
                .build();

        accountRepository.saveAccountIdentifier(account, accountIdentifier);
    }

    public void joinGroup() {
        GroupMember groupMember = new GroupMember();
        groupRepository.saveGroupMember(groupMember);
    }

    public void quitGroup() {
        GroupMember groupMember = new GroupMember();
        groupRepository.removeGroupMember(groupMember);
    }
}
